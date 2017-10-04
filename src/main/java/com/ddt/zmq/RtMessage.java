package com.ddt.zmq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

class RtTimestamp {
    private long m_tstamp;
    private RtMessageContext m_context;

    public RtTimestamp(final RtMessageContext m_context, final long m_tstamp) {
        this.m_tstamp = m_tstamp;
        this.m_context = m_context;
    }
}

class RtMessageData {
    public final static String MAGIC = "MCRTMsgs"; // Multicast real time message magic
    public final static int VERSION = 1; // RtMessage version
    public char[] magic; // magic identifier
    public int version; // message version
    public RtMessageType type;
    public long timestamps;
    public long reserved;
    public long size;
    public char[] data;
}

public class RtMessage implements Serializable{
    private static final long serialVersionUID = -2142501523786246610L;
    protected RtMessageType m_type;
    protected Object m_data;
    protected long m_dataSize;
    protected ArrayList<RtTimestamp> m_timestamps;

    public RtMessage(RtMessage message) {
        m_type = message.m_type;

        m_data = new Object(); // TODO
        m_data = message.m_data;

        m_dataSize = message.m_dataSize;
        m_timestamps = message.m_timestamps;
    }

    public RtMessage(RtMessageType msgType){
        m_type = msgType;
        m_data = null;
        m_dataSize = 0;
    }

    public RtMessage(RtMessageType msgType, RtMessage prevMsg){
        m_type = msgType;
        m_data = null;
        m_dataSize = 0;

        ArrayList<RtTimestamp> timestamps = prevMsg.getTimestamps();
        m_timestamps.addAll(timestamps);
    }

    public void addTimestamp(RtMessageContext m_context, long tstamp)
    {
        m_timestamps.add(new RtTimestamp(m_context, tstamp));
    }

    public ArrayList<RtTimestamp> getTimestamps(){
        return m_timestamps;
    }

    public RtMessageType getType() {
        return m_type;
    }

    public void setType(RtMessageType type) {
        this.m_type = type;
    }

    public Object getData() {
        return m_data;
    }

    public long getDataSize(){
        return m_dataSize;
    }
}
