package com.ddt.zmq;

public enum RtMessageContext {
    MCTX_UNKNOWN("MCTX_UNKNOWN"),
    MCTX_DATA("MCTX_DATA");

    private final String name;

    RtMessageContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
