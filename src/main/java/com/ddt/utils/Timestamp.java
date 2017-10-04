package com.ddt.utils;

public class Timestamp {
    private long value;

    public Timestamp(){
        this.value = 0;
    }

    public Timestamp(final long value){
        this.value = value;
    }

    public Timestamp(final Timestamp rhs){
        this.value = rhs.value;
    }

    public static Timestamp getNow(){
        return new Timestamp(System.nanoTime());
    }

}
