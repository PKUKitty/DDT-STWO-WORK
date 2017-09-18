package com.ddt.zmq;

import com.ddt.utils.SimpleThread;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class RtMessageSocketFactory {

    private static final String ZMQ_CONFIG_MAX_SOCKETS = "max_sockets";
    private static final String ZMQ_CONFIG_IO_THREADS = "io_threads";

    private ZMQ.Context m_zmqContext = null;

    private HashSet<RtMessagePublisher> m_publishers = null;
    private HashSet<RtMessageSubscriber> m_subscribers = null;
    private HashSet<RtMessagePusher> m_pushers = null;
    private HashSet<RtMessagePuller> m_pullers = null;

    private FactoryStats m_factoryStats = null;


    public RtMessageSocketFactory(String statsFilePath, long updateIntervalInSec) {
        this.m_zmqContext = null;
        this.m_publishers = new HashSet<>();
        this.m_subscribers = new HashSet<>();
        this.m_pushers = new HashSet<>();
        this.m_pullers = new HashSet<>();
        if ((statsFilePath != null) && (updateIntervalInSec != 0)) {
            m_factoryStats = new FactoryStats(statsFilePath, updateIntervalInSec, this);
        }
    }

    public synchronized boolean open(int ioThreads /* = 2*/, int maxSockets /* = 256*/) {
        if (null != m_zmqContext) {
            System.out.println("Double RtMessageSocketFactory::open()!");
            return false;
        }

        System.out.printf("RtMessageSocketFactory::open(ioThreads = %d, maxSockets = %d)", ioThreads, maxSockets);
        try {
            m_zmqContext = ZMQ.context(1);
        } catch (ZMQException e) {
            e.printStackTrace();
        }

        if (null == m_zmqContext) {
            return false;
        }

        //set max socket and io threads; //TODO;

        if (m_factoryStats != null) {
            m_factoryStats.start();
        }
        return true;
    }


    public boolean isOpen() {
        return null != m_zmqContext;
    }

    public synchronized boolean close() {
        if (!isOpen()) {
            return true;
        }

        if (m_factoryStats != null) {
            m_factoryStats.stop();
            m_factoryStats = null;
        }

        for (RtMessagePublisher pub : m_publishers) {
            pub.stop();
        }
        m_publishers.clear();

        for (RtMessageSubscriber sub : m_subscribers) {
            sub.stop();
        }
        m_subscribers.clear();


        for (RtMessagePusher push : m_pushers) {
            push.stop();
        }
        m_pushers.clear();


        for (RtMessagePuller pull : m_pullers) {
            pull.stop();
        }
        m_pullers.clear();

        try {
            m_zmqContext.close();
        } catch (ZMQException e) {
            e.printStackTrace();
            System.out.printf("SocketFactory: Error destroying ZeroMQ context");
            m_zmqContext = null;
            return false;
        }

        m_zmqContext = null;
        return true;
    }

    public synchronized RtMessagePublisher createPublisher(String name, RtMessageContext context,
                                                           long bufferSize, int zmqBufferSize) {
        if (!isOpen()) {
            System.out.printf("RtMessageSocketFactory can't create sockets before being open!");
            return null;
        }
        RtMessagePublisher ret = new RtMessagePublisher(name, context, m_zmqContext, bufferSize, zmqBufferSize);
        m_publishers.add(ret);
        return ret;
    }

    public synchronized RtMessageSubscriber createSubscriber(String name, RtMessageContext context,
                                                             long bufferSize, int zmqBufferSize) {
        if (!isOpen()) {
            System.out.printf("RtMessageSocketFactory can't create sockets before being open!");
            return null;
        }
        RtMessageSubscriber ret = new RtMessageSubscriber(name, context, m_zmqContext, bufferSize, zmqBufferSize);
        m_subscribers.add(ret);
        return ret;
    }

    public synchronized RtMessagePusher createPusher(String name, RtMessageContext context,
                                                     long bufferSize, int zmqBufferSize) {
        if (!isOpen()) {
            System.out.printf("RtMessageSocketFactory can't create sockets before being open!");
            return null;
        }
        RtMessagePusher ret = new RtMessagePusher(name, context, m_zmqContext, bufferSize, zmqBufferSize);
        m_pushers.add(ret);
        return ret;
    }

    public synchronized RtMessagePuller createPuller(String name, RtMessageContext context,
                                                     long bufferSize, int zmqBufferSize) {
        if (!isOpen()) {
            System.out.printf("RtMessageSocketFactory can't create sockets before being open!");
            return null;
        }

        RtMessagePuller ret = new RtMessagePuller(name, context, m_zmqContext, bufferSize, zmqBufferSize);
        m_pullers.add(ret);
        return ret;
    }

    public synchronized boolean destroyPublisher(RtMessagePublisher publisher) {
        if (!m_publishers.contains(publisher)) {
            System.out.printf("RtMessageSocketFactory::destroyPublisher() called with invalid publisher");
            return false;
        }

        if (publisher.isRunning()) {
            System.out.printf("RtMessageSocketFactory::destroyPublisher() called with running publisher");
            publisher.stop();
        }
        m_publishers.remove(publisher);
        return true;
    }

    public synchronized boolean destroySubscriber(RtMessageSubscriber subscriber) {
        if (!m_subscribers.contains(subscriber)) {
            System.out.printf("RtMessageSocketFactory::destroySubscriber() called with invalid subscriber");
            return false;
        }
        if (subscriber.isRunning()) {
            System.out.printf("RtMessageSocketFactory::destroySubscriber() called with running subscriber");
            subscriber.stop();
        }
        m_subscribers.remove(subscriber);
        return true;
    }

    public synchronized boolean destroyPusher(RtMessagePusher pusher) {
        if (!m_pushers.contains(pusher)) {
            System.out.printf("RtMessageSocketFactory::destroyPusher() called with invalid pusher");
            return false;
        }
        if (pusher.isRunning()) {
            System.out.printf("RtMessageSocketFactory::destroyPusher() called with running pusher");
            pusher.stop();
        }
        m_pushers.remove(pusher);
        return true;
    }

    public synchronized boolean destroyPuller(RtMessagePuller puller) {
        if (!m_pullers.contains(puller)) {
            System.out.printf("RtMessageSocketFactory::destroyPuller() called with invalid puller");
            return false;
        }
        if (puller.isRunning()) {
            System.out.printf("RtMessageSocketFactory::destroyPuller() called with running puller");
            puller.stop();
        }
        m_pullers.remove(puller);
        return true;
    }

    protected synchronized void writeStats(String filePath) {
        if (!isOpen()) {
            return;
        } else {
            //TODO write file;
        }
    }
}


class FactoryStats extends SimpleThread {

    private RtMessageSocketFactory m_factory = null;

    private String m_filePath = null;

    private long m_updateIntervalInSec = 10;


    public FactoryStats(String filePath, long updateIntervalInSec, RtMessageSocketFactory factory) {
        super("RtMsgStats");
        m_factory = factory;
        m_filePath = filePath;
        m_updateIntervalInSec = updateIntervalInSec;
    }

    @Override
    protected void runMethod() {
        while (shouldRun()) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    m_factory.writeStats(m_filePath);
                }
            }, 0, m_updateIntervalInSec);
        }
    }
}