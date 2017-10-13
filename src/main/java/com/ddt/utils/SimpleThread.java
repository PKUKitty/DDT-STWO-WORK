package com.ddt.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SimpleThread implements Runnable {

    protected Thread m_thread = null;

    protected String m_name;

    protected int m_priority = 5; // default setting;

    protected AtomicBoolean m_stopping = null;
    protected AtomicBoolean m_stopped = null;
    protected AtomicBoolean m_pausing = null;
    protected AtomicBoolean m_paused = null;

    public SimpleThread(final String threadName) {
        this.m_name = threadName;
        this.m_stopping = new AtomicBoolean(false);
        this.m_stopped = new AtomicBoolean(true);
        this.m_pausing = new AtomicBoolean(false);
        this.m_paused = new AtomicBoolean(false);
    }

    public SimpleThread(final String threadName, final int priority) {
        this.m_name = threadName;
        this.m_priority = priority;
        this.m_stopping = new AtomicBoolean(false);
        this.m_stopped = new AtomicBoolean(true);
        this.m_pausing = new AtomicBoolean(false);
        this.m_paused = new AtomicBoolean(false);
    }

    public String getName() {
        return m_name;
    }

    public int getPriority() {
        return m_priority;
    }

    public void setPriority(final int priority) {
        this.m_priority = priority;
    }

    public boolean isRunning() {
        return !m_stopped.get();
    }

    public boolean isPaused() {
        return m_paused.get();
    }

    protected boolean shouldRun() {
        return !(m_pausing.get() || m_stopping.get());
    }

    public synchronized boolean start() {
        if (isRunning()) {
            return false;
        }

        if (m_thread == null) {
            m_thread = new Thread(this, m_name);
            m_thread.setPriority(m_priority);

            if (!beforeStart()) {
                return false;
            }

            m_stopped.set(false);
            m_stopping.set(false);
            m_paused.set(false);
            m_pausing.set(false);

            String tmpName = m_name;
            if (m_name.length() > 15) {
                tmpName = m_name.substring(0, 15);
            }
            m_thread.setName(tmpName);

            m_thread.start();
            return true;
        }
        return false;
    }

    public synchronized boolean stop() {
        if (!isRunning()) {
            return false;
        }

        if (!m_stopping.get()) {
            beforeStop();
        }

        if (isPaused()) {
            m_stopped.set(true);
            m_stopping.set(false);
            m_paused.set(false);
            m_pausing.set(false);
            afterStop();
            return true;
        }

        m_stopping.set(true);

        if (m_thread != null) {
            m_thread.stop();
            afterStop();
            return true;
        }
        return false;
    }

    public synchronized boolean pause() {
        if (!isRunning()) {
            return false;
        }

        if (m_stopped.get()) {
            return false;
        }

        beforePause();
        m_pausing.set(true);
        m_thread.stop();
        afterPause();
        m_thread = null;
        return true;
    }

    public synchronized boolean resume() {
        if (!isRunning()) {
            return false;
        }

        if (!isPaused()) {
            return false;
        }


        if (m_thread == null) {
            m_thread = new Thread(this, m_name);

            if (!isPaused()) {
                return false;
            }

            m_stopped.set(false);
            m_stopping.set(false);
            m_paused.set(false);
            m_pausing.set(false);

            String tmpName = m_name;
            if (m_name.length() > 15) {
                tmpName = m_name.substring(0, 15);
            }
            m_thread.setName(tmpName);

            m_thread.start();
            return true;
        }
        return false;
    }

    protected abstract void runMethod();

    @Override
    public void run() {
        runMethod();
    }


    protected boolean beforeStart() {
        return true;
    }

    protected boolean beforePause() {
        return true;
    }

    protected boolean afterPause() {
        return true;
    }

    protected boolean beforeStop() {
        return true;
    }

    protected boolean afterStop() {
        return true;
    }

    @Override
    public String toString() {
        return this.m_name;
    }
}
