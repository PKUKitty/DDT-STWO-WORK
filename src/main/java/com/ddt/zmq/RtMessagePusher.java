package com.ddt.zmq;

import org.zeromq.ZMQ;

public class RtMessagePusher extends RtMessageSender {
    protected RtMessagePusher(String name, RtMessageContext context, ZMQ.Context zmqContext, long bufferSize, int zmqBufferSize) {
        super(name, context, zmqContext, bufferSize, zmqBufferSize);
        m_zmqSocketType = ZMQ.PUSH;
    }

    public boolean push(RtMessage msg, long timeout) {
        if (!isZmqOpen()) {
            System.out.println("Can't send messages via '%s' before starting or after stopping it!");
            return false;
        }

        if (null == msg) {
            System.out.println("Sending NULL message via");
            return false;
        }

        return m_buffer.add(msg, timeout);
    }
}
