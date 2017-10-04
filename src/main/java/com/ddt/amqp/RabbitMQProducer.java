package com.ddt.amqp;

import java.io.IOException;

public class RabbitMQProducer extends RabbitMQBase {
    public RabbitMQProducer(final String host, final int port, final String queueName, final String userName, final String password) {
        super(host, port, queueName, userName, password);
    }

    public void sendMessage(final String message) {
        try {
            channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
