package com.ddt.utils;

import java.io.Serializable;
import java.lang.*;


public class AdList implements Serializable{

    private static final long serialVersionUID = -1760593987648063035L;

    private AdListNode m_head = null;

    private AdListNode m_tail = null;

    private int m_len = 0;

    private FunType m_funType = null;


    public void setFunType(FunType funType) {
        this.m_funType = funType;
    }

    /**
     * @return true if the adlist is empty; else return false;
     */
    public boolean empty() {
        return (m_len == 0);
    }

    /**
     * get adlist length;
     *
     * @return m_len;
     */
    public int getLength() {
        return m_len;
    }

    public AdListNode getHead() {
        return m_head;
    }

    public AdListNode getTail() {
        return m_tail;
    }

    public boolean compare(Object key1, Object key2) {
        if (m_funType == null) {
            return key1 == key2;
        }
        return m_funType.compare(key1, key2);
    }

    public Object objectDup(Object other) {
        if (m_funType == null) {
            return other;
        }
        return m_funType.objectDup(other);
    }

    /**
     * append node into list head;
     *
     * @param value object
     * @return adlist
     */
    public AdList addNodeHead(Object value) {
        AdListNode node = new AdListNode();
        node.value = value;

        if (0 == m_len) {
            m_head = m_tail = node;
            node.next = null;
            node.prev = null;
        } else {
            node.prev = null;
            node.next = m_head;
            m_head.prev = node;
            m_head = node;
        }
        m_len++;
        return this;
    }

    /**
     * append node into list tail;
     *
     * @param value object;
     * @return adlist
     */
    public AdList addNodeTail(Object value) {
        AdListNode node = new AdListNode();
        node.value = value;
        if (0 == m_len) {
            m_head = m_tail = node;
            node.prev = null;
            node.next = null;
        } else {
            node.prev = m_tail;
            node.next = null;
            m_tail.next = node;
            m_tail = node;
        }
        m_len++;
        return this;
    }

    /**
     * insert node;
     *
     * @param oldNode old node for insert node;
     * @param value   insert node's value;
     * @param after   direction;
     * @return list;
     */
    public AdList insertNode(AdListNode oldNode, Object value, boolean after) {
        AdListNode node = new AdListNode();
        node.value = value;
        if (after) {
            node.prev = oldNode;
            node.next = oldNode.next;

            if (oldNode.next != null) {
                oldNode.next.prev = node;
            } else {
                m_tail = node;
            }
            oldNode.next = node;
        } else {
            node.prev = oldNode.prev;
            node.next = oldNode;

            if (oldNode.prev != m_head/*oldNode.prev != null*/) {
                oldNode.prev.next = node;
                oldNode.prev = node;
            } else {
                m_head = node;
            }

        }
        m_len++;
        return this;
    }

    /**
     * delete node;
     *
     * @param node list node
     */
    public void delNode(AdListNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            m_head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            m_tail = node.prev;
        }
        m_len--;
    }

    public AdListNode searchKey(Object key) {
        if (0 == m_len) {
            return null;
        }
        AdListNode node = m_head;
        while (node != null) {
            if (compare(node.value, key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public AdListNode searchIndex(int index) {
        AdListNode node;
        if (index < 0) {
            index = (-index) - 1;
            node = m_tail;
            while (((index--) != 0) && (node != null)) {
                node = node.prev;
            }
        } else {
            node = m_head;
            while (((index--) != 0) && (node != null)) {
                node = node.next;
            }
        }
        return node;
    }

    /**
     * swap head and tail;
     */
    public void rotate() {
        if (m_len <= 1) {
            return;
        }
        AdListNode tail = m_tail;
        m_tail = m_tail.prev;
        m_tail.next = null;

        /* Move it as head */
        m_head.prev = tail;
        tail.prev = null;
        tail.next = m_head;
        m_head = tail;
    }

    public void join(AdList other) {
//        if(other.empty()){
//            return;
//        }
//
//        if(empty()){
//            m_head = other.m_head;
//            m_tail = other.m_tail;
//        }

        if (other.m_head != null) {
            other.m_head.prev = m_tail;
        }

        if (m_tail != null) {
            m_tail.next = other.m_head;
        } else { // list is null;
            m_head = other.m_head;
        }


        m_tail = other.m_tail;
        m_len += other.m_len;

        other.m_head = other.m_tail = null;
        other.m_len = 0;
    }
}
