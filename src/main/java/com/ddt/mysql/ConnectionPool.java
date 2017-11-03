/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.mysql;

import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;

public class ConnectionPool {

    private String jdbcDriver = "";

    private String dbUrl = "";

    private String dbUserName = "";

    private String dbPassword = "";

    private String tableName = "";

    private static final int initialConnections = 1;

    private static final int incrementalConnections = 5;

    private int maxConnections = 20;

    private Vector<PooledConnection> connections = null;

    public ConnectionPool(String jdbcDriver, String dbUrl, String dbUserName, String dbPassword, String tableName) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        this.tableName = tableName;

        try {
            createPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getInitialConnections() {
        return initialConnections;
    }

    public int getIncrementalConnections() {
        return incrementalConnections;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    private synchronized void createPool() throws Exception {
        if (connections != null) {
            return;
        }

        Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
        DriverManager.registerDriver(driver);

        connections = new Vector<>();
        createConnections(initialConnections);
    }

    private synchronized void createConnections(int numConnections) throws SQLException {
        for (int x = 0; x < numConnections; x++) {
            if (this.maxConnections > 0 && this.connections.size() >= this.maxConnections) {
                break;
            }

            // add a new PooledConnection object to connections vector
            try {
                connections.addElement(new PooledConnection(newConnection()));
            } catch (SQLException e) {
                throw new SQLException();
            }
        }
    }

    private Connection newConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        if (connections.size() == 0) {

            DatabaseMetaData metaData = conn.getMetaData();

            int driverMaxConnections = metaData.getMaxConnections();

            if (driverMaxConnections > 0 && this.maxConnections > driverMaxConnections) {
                this.maxConnections = driverMaxConnections;
            }
        }
        return conn;
    }

    public synchronized PooledConnection getConnection() throws SQLException {
        if (connections == null) {
            return null;
        }

        PooledConnection conn = getFreeConnection();

        while (conn == null) {
            wait(250);
            conn = getFreeConnection();
        }
        return conn;
    }


    private PooledConnection getFreeConnection() throws SQLException {
        PooledConnection conn = findFreeConnection();
        if (conn == null) {
            createConnections(incrementalConnections);
            conn = findFreeConnection();
            if (conn == null) {
                return null;
            }
        }
        return conn;
    }


    private PooledConnection findFreeConnection() throws SQLException {
        for (int i = 0; i < connections.size(); i++) {
            PooledConnection pc = connections.elementAt(i);
            if (!pc.isBusy()) {
                Connection conn = pc.getConnection();
                pc.setBusy(true);
                if (!isValid(conn)) {
                    try {
                        conn = newConnection();
                        pc.setConnection(conn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        connections.remove(i--);
                        continue;
                    }
                }
                return pc;
            }
        }
        return null;
    }

    private boolean isValid(Connection conn) {
        try {
            return conn.isValid(3000);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void returnConnection(Connection conn) {
        if (connections == null) {
            return;
        }

        PooledConnection pConn;
        Enumeration<PooledConnection> enumerate = connections.elements();

        while (enumerate.hasMoreElements()) {
            pConn = enumerate.nextElement();
            if (conn == pConn.getConnection()) {
                pConn.setBusy(false);
                break;
            }
        }
    }

    public synchronized void refreshConnections() throws SQLException {
        if (connections == null) {
            return;
        }

        PooledConnection pConn;
        Enumeration<PooledConnection> enumerate = connections.elements();

        while (enumerate.hasMoreElements()) {
            pConn = enumerate.nextElement();
            if (pConn.isBusy()) {
                wait(5000);
            }

            closeConnection(pConn.getConnection());
            pConn.setConnection(newConnection());
            pConn.setBusy(false);
        }
    }

    public synchronized void closeConnectionPool() throws SQLException {
        if (connections == null) {
            return;
        }

        PooledConnection pConn;
        Enumeration<PooledConnection> enumerate = connections.elements();

        while (enumerate.hasMoreElements()) {
            pConn = enumerate.nextElement();
            if (pConn.isBusy()) {
                wait(5000);
            }

            closeConnection(pConn.getConnection());
            connections.removeElement(pConn);
        }
        connections = null;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class PooledConnection {

        private Connection connection = null;

        private boolean busy;

        private PooledConnection(Connection connection) {
            this.connection = connection;
        }

        public ResultSet executeQuery(String sql) throws SQLException {
            return connection.createStatement().executeQuery(sql);
        }

        public int executeUpdate(String sql) throws SQLException {
            return connection.createStatement().executeUpdate(sql);
        }

        private Connection getConnection() {
            return connection;
        }

        private void setConnection(Connection connection) {
            this.connection = connection;
        }

        private boolean isBusy() {
            return busy;
        }

        private void setBusy(boolean busy) {
            this.busy = busy;
        }

        public void close() {
            busy = false;
        }
    }
}
