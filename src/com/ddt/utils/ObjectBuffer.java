package com.ddt.utils;

import java.util.Vector;

public class ObjectBuffer<T> {
    protected int m_size = 0;
    protected int m_start = 0;
    protected int m_end = 0;
    protected int m_free = 0;
    protected Vector<T> m_data = null;

    public ObjectBuffer(final int size) {
        m_size = size;
        m_start = 0;
        m_end = 0;
        m_free = size;
        m_data = new Vector(size);
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

    public synchronized boolean add(final T msg) {
        assert (null != msg);

        if (m_free < 1) {
            return false;
        }
        m_data.set(m_end, msg);
        --m_free;
        m_end = (m_end + 1) % m_size;
        return true;
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
}
