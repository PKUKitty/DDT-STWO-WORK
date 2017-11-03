package com.ddt.utils;

import java.util.concurrent.TimeUnit;

public class Timer {

    //    public final static long SECOND_IN_NS = 1000000000L; // 1 second in nanoseconds
    public final static long SECOND_IN_NS = TimeUnit.SECONDS.toNanos(1); // 1 second in nanoseconds

    private long m_start;

    private long m_end;

    public Timer(boolean immediateStart) {
        if (immediateStart) {
            start();
        }
    }

    public void start() {
        m_end = m_start = System.nanoTime();
    }

    public void stop() {
        m_end = System.nanoTime();
    }

    public double getNseconds() {
        return m_end - m_start;
    }

    public double getSeconds() {
        return (getNseconds() / SECOND_IN_NS);
    }

    public double getSecondsAndContinue() {
        return (System.nanoTime() - m_start) / SECOND_IN_NS;
    }
}

