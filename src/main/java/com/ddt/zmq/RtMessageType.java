package com.ddt.zmq;

public enum RtMessageType {
    RTM_INVALID("RTM_INVALID"),
    RTM_DATA("RTM_DATA");

    private final String name;

    RtMessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
