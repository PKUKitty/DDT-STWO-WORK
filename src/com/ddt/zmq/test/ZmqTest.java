package com.ddt.zmq.test;

import com.ddt.zmq.*;

public class ZmqTest {
    public static void main(String[] args) {
        //use zmq factory;
        RtMessageSocketFactory socketFactory = new RtMessageSocketFactory(null, 0);
        socketFactory.open(2, 256);
        long bufferSize = 1024;
        int zmqBufferSize = 0;
        RtMessageContext context = RtMessageContext.MCTX_DATA;
        RtMessagePublisher publisher = socketFactory.createPublisher("Publisher", context, bufferSize, zmqBufferSize);
        publisher.configure("tcp://127.0.0.1:5556", true);
        publisher.start();
        RtMessage msg = new RtMessage(RtMessageType.RTM_DATA);
        publisher.publish(msg, 0);


        socketFactory.destroyPublisher(publisher);
        socketFactory.close();
    }
}
