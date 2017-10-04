package com.ddt.zmq;


import com.ddt.utils.ObjectBuffer;
import com.ddt.utils.Serialize;
import com.ddt.utils.SimpleThread;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class RtMessageSocket extends SimpleThread {

    public static final long SECOND_IN_NS = 1000000000l; // 1 second in nanoseconds

    public static final long STATS_INTERVAL = SECOND_IN_NS * 60;

    public static final int RT_TX_RATE = 64 * 8 * 1024; // kilobits per second -> 64 MiB / sec
    public static final int RT_SNDBUF = 65536;
    public static final int RT_RCVBUF = 65536;
    public static final int RT_LINGER = 2000; // 2 seconds
    public static final int RT_BACKLOG = 1024;
    public static final long RT_MAX_MSG_SIZE = 5242880;
    public static final int RT_MCAST_HOPS = 1;
    public static final int RT_RECONNECT_IVL = 10; // 1/100 second, default is 100
    public static final int RT_RECONNECT_IVL_MAX = 1024; // 1 second, Default is 0, only use ZMQ_RECONNECT_IVL
    public static final int RT_RECOVERY_IVL = 4096; // 4 seconds
    public static final long RT_SENDER_SLEEP = SECOND_IN_NS / 200; // sleep interval when the buffer is empty, nanoseconds
    public static final long RT_RECEIVER_SLEEP = SECOND_IN_NS / 200; // sleep interval when no messages, nanoseconds
    public static final long RT_MSGS_PER_SECOND = 4096; // max messages per second

    protected ZMQ.Context m_zmqContext = null;
    protected ZMQ.Socket m_socket = null;
    protected int m_zmqSocketType = -1;
    protected long m_bufferSize = 0;
    protected int m_zmqBufferSize = 0;
    protected String m_endpoint = "";
    protected boolean m_bind = false;
    protected RtMessageContext m_context = null;
    protected ObjectBuffer<RtMessage> m_buffer = null;
    protected long m_totalMessages = 0; // number of messages
    protected long m_totalRunningTime = 0; // nanoseconds
    protected long m_totalSleepTime = 0; // nanoseconds
    protected long m_lastStartedAt = 0; // to calculate running time


    public void configure(String endpoint, boolean bind) {
        m_endpoint = endpoint;
        m_bind = bind;
    }

    public String getEndpoint() {
        return m_endpoint;
    }

    public RtMessage createMessage(RtMessageType msgType) {
        return new RtMessage(msgType);
    }

    public RtMessage createMessage(RtMessageType msgType, RtMessage prevMsg) {
        return new RtMessage(msgType, prevMsg);
    }

    //TODO fix it;
//    public void freeMessage(RtMessage msg) {
//        msg = NULL;
//    }

    protected RtMessageSocket(String name, RtMessageContext context, ZMQ.Context zmqContext,
                              long bufferSize, int zmqBufferSize) {
        super(name);
        m_zmqContext = zmqContext;
        m_socket = null;
        m_zmqSocketType = -1;
        m_bufferSize = bufferSize;
        m_zmqBufferSize = zmqBufferSize;
        m_bind = false;
        m_context = context;
        m_buffer = new ObjectBuffer<>((int) bufferSize);
        m_totalMessages = 0;
        m_totalRunningTime = 0;
        m_totalSleepTime = 0;
        m_lastStartedAt = 0;

        assert (null != zmqContext);
        assert (bufferSize > 0);
        if (m_zmqBufferSize < 1) {
            if (m_bufferSize < Integer.MAX_VALUE) {
                m_zmqBufferSize = (int) m_bufferSize;
            } else {
                m_zmqBufferSize = Integer.MAX_VALUE;
            }
        }
    }

    public synchronized boolean openZmqSocket() {
        if (isZmqOpen()) {
            System.out.println("Re-opening ZMQ socket without close");
            return false;
        }

        if (!isConfigured()) {
            System.out.println("Re-opening ZMQ socket without configuring endpoint");
            return false;
        }

        try {
            m_socket = m_zmqContext.socket(m_zmqSocketType);
        } catch (ZMQException e) {
            System.out.println("Create socket error info: " + e.toString());
        }

        if (null == m_socket) {
            System.out.println("Failed to create ZeroMQ socket");
            return false;
        }

        m_socket.setHWM(m_zmqBufferSize);
        m_socket.setRate(RT_TX_RATE);
        m_socket.setSendBufferSize(RT_SNDBUF);
        m_socket.setReceiveBufferSize(RT_RCVBUF);
        m_socket.setLinger(RT_LINGER);
        m_socket.setReconnectIVL(RT_RECONNECT_IVL);
        m_socket.setReconnectIVLMax(RT_RECONNECT_IVL_MAX);
        m_socket.setBacklog(RT_BACKLOG);
        m_socket.setMaxMsgSize(RT_MAX_MSG_SIZE);
        m_socket.setMulticastHops(RT_MCAST_HOPS);
        m_socket.setReceiveTimeOut(0);
        m_socket.setSendTimeOut(-1);
        m_socket.setIPv4Only(true);
        m_socket.setRecoveryInterval(RT_RECOVERY_IVL);

//        if (m_zmqSocketType == ZMQ.SUB) {
//            if (0 != zmq_setsockopt(m_socket, ZMQ_SUBSCRIBE, "", 0)) {
//                Log::error ("zmq_setsockopt ZMQ_SUBSCRIBE for '%s': %s", m_name.c_str(), zmq_strerror(errno));
//                return false;
//            }
//        }

        if (m_bind) {
            // bind
            try {
                m_socket.bind(m_endpoint);
            } catch (ZMQException e) {
                System.out.println("bind error info: " + e.toString());
                e.printStackTrace();
            }

        } else {
            // connect
            try {
                m_socket.connect(m_endpoint);
            } catch (ZMQException e) {
                System.out.println("connect error info: " + e.toString());
                e.printStackTrace();
            }
        }
        return true;
    }

    public synchronized boolean closeZmqSocket() {
        if (null == m_socket) {
            return false;
        }

        try {
            m_socket.close();
            m_socket = null;
        } catch (ZMQException e) {
            System.out.println("close error info: " + e.toString());
        }
        return false;
    }

    public boolean isConfigured() {
        return !m_endpoint.isEmpty();
    }

    public boolean isZmqOpen() {
        return m_socket != null;
    }


    protected abstract void runMethod();

    protected boolean beforeStart() {
        m_lastStartedAt = System.currentTimeMillis();
        boolean ret = openZmqSocket();
        if (!ret) {
            closeZmqSocket();
        }
        return ret;
    }

    protected boolean afterPause() {
        m_totalRunningTime += System.currentTimeMillis() - m_lastStartedAt;
        return true;
    }

    protected boolean beforeResume() {
        m_lastStartedAt = System.currentTimeMillis();
        return true;
    }

    protected boolean afterStop() {
        m_totalRunningTime += System.currentTimeMillis() - m_lastStartedAt;
//        double totalRunningTime = (double)m_totalRunningTime / SECOND_IN_NS;
        boolean ret = closeZmqSocket();
        return ret;
    }
}


class RtMessageSender extends RtMessageSocket {

    protected RtMessageSender(String name, RtMessageContext context, ZMQ.Context zmqContext, long bufferSize, int zmqBufferSize) {
        super(name, context, zmqContext, bufferSize, zmqBufferSize);
    }

    @Override
    protected void runMethod() {
        try {
            m_socket.setSendTimeOut(0);
        } catch (ZMQException e) {
            System.out.println("runMethod() set send timeout error");
            e.printStackTrace();
        }

        boolean speedLimited = (m_zmqSocketType == ZMQ.PUB);
        while (shouldRun()) {
            RtMessage msg = m_buffer.get();
            if (null == msg) {
                try {
                    Thread.sleep(RT_SENDER_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m_totalSleepTime += RT_SENDER_SLEEP;
            } else {
                byte[] bytes = Serialize.object2Byte(msg); //TODO recheck it;
                try {
                    m_socket.send(bytes, bytes.length);
                } catch (ZMQException e) {
                    System.out.println("sender send bytes error");
                    e.printStackTrace();
                }
                ++m_totalMessages;
            }

            if (speedLimited) {
                //TODO
            }
        }
    }
}

class RtMessageReceiver extends RtMessageSocket {

    protected RtMessageReceiver(String name, RtMessageContext context, ZMQ.Context zmqContext, long bufferSize, int zmqBufferSize) {
        super(name, context, zmqContext, bufferSize, zmqBufferSize);
    }


    @Override
    protected void runMethod() {
        try {
            m_socket.setReceiveTimeOut(0);
        } catch (ZMQException e) {
            System.out.println("RUN set receive timeout error");
            e.printStackTrace();
        }

        while (shouldRun()) {
            byte[] recvBytes = null;
            try {
                recvBytes = m_socket.recv();
            } catch (ZMQException e) {
                e.printStackTrace();
            }

            if (recvBytes == null) {
                try {
                    Thread.sleep(RT_RECEIVER_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m_totalSleepTime += RT_SENDER_SLEEP;
            } else {
                RtMessage msg = (RtMessage) Serialize.byte2Object(recvBytes);
                if (null != msg) {
                    msg.addTimestamp(m_context, System.currentTimeMillis());
                    boolean ret = m_buffer.add(msg, 10);
                    if (!ret) {
                        System.out.println("receive msg append buffer error");
                        return;
                    }
                    ++m_totalMessages;
                }
            }
        }
    }
}
