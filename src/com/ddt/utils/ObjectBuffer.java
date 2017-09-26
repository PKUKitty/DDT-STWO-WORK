package com.ddt.utils;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ObjectBuffer<T> {
    protected int m_size = 0;
    protected int m_start = 0;
    protected int m_end = 0;
    protected int m_free = 0;
    protected ArrayList<T> m_data = null;

    public ObjectBuffer(final int size) {
        m_size = size;
        m_start = 0;
        m_end = 0;
        m_free = size;
        m_data = new ArrayList<>(size);
    }

    public synchronized int getSize() {
        return m_size;
    }

    public synchronized int getFree() {
        return m_free;
    }

    public synchronized int getUsed() {
        return (m_size - m_free);
    }

    /**
     * add object into buffer
     *
     * @param msg     object message
     * @param timeout time out by ms;
     * @return add success return true, else return false;
     */
    public synchronized boolean add(final T msg, long timeout) {
        assert (null != msg);

        if (timeout == 0) {
            if (m_free < 1) {
                return false;
            }
            m_data.add(m_end, msg);
            --m_free;
            m_end = (m_end + 1) % m_size;
            return true;
        }

        Timer timer = new Timer(true);
        double end = timer.getNseconds() + timeout;
        while (timer.getNseconds() <= end) {
            synchronized (this) {
                if (m_free > 0) {
                    m_data.add(m_end, msg);
                    --m_free;
                    m_end = (m_end + 1) % m_size;
                    return true;
                }
            }

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.stop();
        }
        return false;
    }


    public synchronized T get() {
        if (m_size == m_free) {
            return null;
        }
        T ret = m_data.get(m_start);
        ++m_free;
        m_start = (m_start + 1) % m_size;
        return ret;
    }

    public void clear() {
        m_data.clear();
        m_start = 0;
        m_end = 0;
    }
}
