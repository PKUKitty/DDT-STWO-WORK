package com.ddt.zmq;

import org.zeromq.ZMQ;

public class RtMessageSubscriber extends RtMessageReceiver {
    protected RtMessageSubscriber(String name, RtMessageContext context, ZMQ.Context zmqContext, long bufferSize, int zmqBufferSize) {
        super(name, context, zmqContext, bufferSize, zmqBufferSize);
        m_zmqSocketType = ZMQ.SUB;
    }

    public RtMessage get() {
        return m_buffer.get();
    }
}
