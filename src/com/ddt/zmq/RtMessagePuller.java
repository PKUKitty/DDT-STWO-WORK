package com.ddt.zmq;

import org.zeromq.ZMQ;

public class RtMessagePuller extends RtMessageReceiver {
    protected RtMessagePuller(String name, RtMessageContext context, ZMQ.Context zmqContext, long bufferSize, int zmqBufferSize) {
        super(name, context, zmqContext, bufferSize, zmqBufferSize);
        m_zmqSocketType = ZMQ.PULL;
    }

    public RtMessage pull() {
        return m_buffer.get();
    }
}
