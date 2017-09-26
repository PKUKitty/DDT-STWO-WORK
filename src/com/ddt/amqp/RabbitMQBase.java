package com.ddt.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class RabbitMQBase {
    protected Channel channel;

    protected Connection connection;

    private final static String HOST_DEFAULT = "localhost";

    private final static int PORT_DEFAULT = 5672;

    protected String queueName;

    public RabbitMQBase(final String host, final int port, final String queueName, final String userName, final String password) {
        this.queueName = queueName;
        ConnectionFactory factory = new ConnectionFactory();

        if (host != null && !host.isEmpty()) {
            factory.setHost(host);
        } else {
            factory.setHost(HOST_DEFAULT);
        }

        if (port > 0) {
            factory.setPort(port);
        } else {
            factory.setPort(PORT_DEFAULT);
        }

        if (userName != null && !userName.isEmpty()) {
            factory.setUsername(userName);
        }

        if (password != null && !password.isEmpty()) {
            factory.setPassword(password);
        }

        try {
            this.connection = factory.newConnection();
            this.channel = connection.createChannel();

            //declaring a queue for this channel. If queue does not exist,
            //it will be created on the server.
            channel.queueDeclare(this.queueName, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * close channel and connection;
     *
     * @throws IOException e
     */
    public void close() throws IOException {
        try {
            this.channel.close();
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
        this.connection.close();
    }
}
