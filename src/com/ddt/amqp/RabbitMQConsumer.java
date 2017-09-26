package com.ddt.amqp;

import com.ddt.utils.ObjectBuffer;
import com.rabbitmq.client.*;

import java.io.IOException;


public class RabbitMQConsumer extends RabbitMQBase implements Runnable, Consumer {

    private ObjectBuffer<String> buffer;

    public RabbitMQConsumer(final String host, final int port, final String queueName, final String userName, final String password, final int bufferSize) {
        super(host, port, queueName, userName, password);
        buffer = new ObjectBuffer<>(bufferSize);
    }

    /**
     * get buffer data;
     *
     * @return buffer data. be care null;
     */
    public String getData() {
        return buffer.get();
    }

    @Override
    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(this.queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     *
     * @param consumerTag string
     */
    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    /**
     * Called when new message is available.
     *
     * @param s
     * @param envelope
     * @param basicProperties
     * @param bytes
     * @throws IOException
     */
    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        String message = new String(bytes, "UTF-8");
        if (!buffer.add(message, 0)) {
            System.out.println("[RabbitMQConsumer] add buffer error, is buffer full?");
        }
    }
}
