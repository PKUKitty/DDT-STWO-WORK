package com.ddt.utils;

import java.util.ArrayList;
import java.util.HashMap;

class DatabasesIndexElement {

    /**
     * redis server hostname;
     * eg. HOSTNAME = "cnd.infra.routeplan.tsk";
     */
    String hostname;


    /**
     * redis server ip; 127.0.0.1
     */
    String ip;

    /**
     * redis port
     */
    int port;

    /**
     * redis database idx
     */
    int idx;

    /**
     * redis database priority
     * min: 0, max:10
     */
    int priority;


    /**
     * usable info
     */
    boolean online;

    /**
     * priority default value is 10; max
     *
     * @param hostname hostname name
     * @param ip       ip
     * @param port     port
     * @param idx      index [0-15] depends on redis config
     */
    DatabasesIndexElement(final String hostname, final String ip, final int port, final int idx) {
        this.hostname = hostname;
        this.ip = ip;
        this.port = port;
        this.idx = idx;
        this.priority = 10;
        this.online = true;
    }

    /**
     * @param hostname hostname
     * @param ip       ip
     * @param port     port
     * @param idx      index    [0-15] depends on redis config
     * @param priority priority [0,10]
     */
    DatabasesIndexElement(final String hostname, final String ip, final int port, final int idx, final int priority) {
        this.hostname = hostname;
        this.ip = ip;
        this.port = port;
        this.idx = idx;
        if (priority <= 10 && priority >= 0) {
            this.priority = priority;
        } else {
            this.priority = 10;
        }
        this.online = true;
    }
}

public class DatabasesIndex {

    /**
     * instance
     */
    private static DatabasesIndex dbIndex = null;

    /**
     * store database index info;
     * eg. LOCATION, hostname:127.0.0.1,    port:6379, idx:0, priority:10;
     * hostname:10.221.2.230, port:6379, idx:0, priority:5;
     * <p>
     * CURRENCY, hostname:127.0.0.1,    port:6379, idx:1, priority:10;
     * hostname:10.221.2.230, port:6379, idx:1, priority:5;
     */
    private static HashMap<Databases, ArrayList<DatabasesIndexElement>> dbIndexMap;

    /**
     * default hostname
     */
    private static final String DEFAULT_HOSTNAME = "cnd.infra.routeplan.tsk";

    /**
     * default ip
     */
    private static final String DEFAULT_IP = "localhost";

    /**
     * default port
     */
    private static final int DEFAULT_PORT = 6379;

    /**
     * default index
     */
    private static final int DEFAULT_INDEX = 0;


    /**
     * get instance
     *
     * @return instance
     */
    public static DatabasesIndex getInstance() {
        if (dbIndex == null) {
            synchronized (DatabasesIndex.class) {
                if (dbIndex == null) {
                    dbIndex = new DatabasesIndex();
                }
            }
        }
        return dbIndex;
    }

    private DatabasesIndex() {
        dbIndexMap = new HashMap<>();
        //TODO config;

        DatabasesIndexElement element = new DatabasesIndexElement("cnd.infra.routeplan.tsk", "localhost", 6379, 0);
        ArrayList<DatabasesIndexElement> lists = new ArrayList<>();
        lists.add(element);
        dbIndexMap.put(Databases.ACTIVE_PORT, lists);
        dbIndexMap.put(Databases.AGENCY, lists);
        dbIndexMap.put(Databases.LOCATION, lists);
        dbIndexMap.put(Databases.FLIGHT_LINE, lists);
    }

    /**
     * get database server count;
     * including off line hostname;
     *
     * @param name database name
     * @return hostname count;
     */
    public final int getServerCounts(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            return dbIndexMap.get(name).size();
        }
        return 0;
    }

    /**
     * get database online hostname count;
     *
     * @param name database name
     * @return online hostname count
     */
    public final int getOnLineServerCounts(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            int onlineCount = 0;
            for (DatabasesIndexElement ele :
                    dbIndexMap.get(name)) {
                if (ele.online) {
                    ++onlineCount;
                }
            }
            return onlineCount;
        }
        return 0;
    }

    /**
     * get database all servers element info;
     *
     * @param name database name
     * @return elements
     */
    public final ArrayList<DatabasesIndexElement> getAllElements(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            return dbIndexMap.get(name);
        }
        return null;
    }

    /**
     * get redis online highest priority hostname of data
     * exclude off line hosts; priority
     *
     * @param name database name
     * @return redis hostname if find the database name, else return default hostname localhost
     */
    public final String getHPHostname(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            return dbIndexMap.get(name).get(getHPIdxOfElements(name)).hostname;
        }
        return DEFAULT_HOSTNAME;
    }


    /**
     * get redis online highest priority ip of data
     * exclude off line servers; priority
     *
     * @param name database name
     * @return redis ip if find the database name, else return default ip localhost
     */
    public final String getHPIp(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            return dbIndexMap.get(name).get(getHPIdxOfElements(name)).ip;
        }
        return DEFAULT_IP;
    }

    /**
     * get redis highest priority port
     * exclude off line hosts;
     *
     * @param name database name
     * @return redis port if find the database name, else return default port 6379
     */
    public final int getHPPort(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            return dbIndexMap.get(name).get(getHPIdxOfElements(name)).port;
        }
        return DEFAULT_PORT;
    }

    /**
     * get redis database highest priority idx
     * exclude off line hosts;
     *
     * @param name database name
     * @return redis database idx if find the database name, else return default idx 0
     */
    public final int getHPIdx(final Databases name) {
        if (dbIndexMap.containsKey(name)) {
            return dbIndexMap.get(name).get(getHPIdxOfElements(name)).idx;
        }
        return DEFAULT_INDEX;
    }


    /**
     * get database elements highest priority index
     *
     * @param name database name
     * @return index
     */
    private int getHPIdxOfElements(final Databases name) {
        int hpIdx = 0;
        int priority = dbIndexMap.get(name).get(0).priority;
        for (int idx = 1; idx < dbIndexMap.get(name).size(); ++idx) {
            if (dbIndexMap.get(name).get(idx).online && dbIndexMap.get(name).get(idx).priority > priority) {
                hpIdx = idx;
                priority = dbIndexMap.get(name).get(idx).priority;
            }
        }
        return hpIdx;
    }
}
