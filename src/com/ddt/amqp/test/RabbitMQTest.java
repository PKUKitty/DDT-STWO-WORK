package com.ddt.amqp.test;

import com.ddt.amqp.RabbitMQConsumer;
import com.ddt.amqp.RabbitMQProducer;

import static java.lang.Thread.sleep;

public class RabbitMQTest {
    public static void main(String[] args) {
        RabbitMQConsumer consumer = new RabbitMQConsumer("localhost", 0, "queue", "admin", "admin", 1024);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        RabbitMQProducer producer = new RabbitMQProducer("localhost", 0, "queue", "admin", "admin");

        for (int i = 0; i < 5; i++) {
            String message = "test";
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }

        while (true) {
            String msg = consumer.getData();
            if (msg != null) {
                System.out.println("data: " + msg.trim());
            } else {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
