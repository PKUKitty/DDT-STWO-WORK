/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LruCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(LruCache.class);

    static class Node {
        String userId;
        UserAccount userAccount;
        Node previous;
        Node next;

        public Node(String userId, UserAccount userAccount) {
            this.userId = userId;
            this.userAccount = userAccount;
            this.previous = null;
            this.next = null;
        }
    }

    int capacity;
    Map<String, Node> cache = new HashMap<>();

    Node head;
    Node tail;

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * get user account;
     *
     * @param userId string: user id;
     * @return object
     */
    public UserAccount get(String userId) {
        if (cache.containsKey(userId)) {
            Node node = cache.get(userId);
            remove(node);
            setHead(node);
            return node.userAccount;
        }
        return null;
    }

    /**
     * remove node from linked list;
     *
     * @param node to be removed node;
     */
    private void remove(Node node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next; // head node;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }
    }

    /**
     * move node to the front of the list
     *
     * @param node insert node
     */
    private void setHead(Node node) {
        node.next = head;
        node.previous = null;
        if (head != null) {
            head.previous = node;
        }

        head = node;

        if (tail == null) {
            tail = head;
        }
    }

    /**
     * set user account
     *
     * @param userId      string user id
     * @param userAccount user account object
     */
    public void set(String userId, UserAccount userAccount) {
        if (cache.containsKey(userId)) {
            Node old = cache.get(userId);
            old.userAccount = userAccount;
            remove(old);
            setHead(old);
        } else {
            Node newNode = new Node(userId, userAccount);
            if (cache.size() >= capacity) {
                LOGGER.info("# Cache is FULL! Removing {} from cache...", tail.userId);
                cache.remove(tail.userId);
                remove(tail);
                setHead(newNode);
            } else {
                setHead(newNode);
            }
            cache.put(userId, newNode);
        }
    }

    public boolean contains(String userId) {
        return cache.containsKey(userId);
    }

    /**
     * Invalidate cache for user
     */
    public void invalidate(String userId) {
        Node toBeRemoved = cache.get(userId);
        if (toBeRemoved != null) {
            LOGGER.info("# {} has been updated! Removing older version from cache...", userId);
            remove(toBeRemoved);
        }
    }

    public boolean isFull() {
        return cache.size() >= capacity;
    }

    public UserAccount getLruData() {
        return tail.userAccount;
    }

    public void clear() {
        head = null;
        tail = null;
        cache.clear();
    }

    /**
     * Returns cache data in list form.
     */
    public List<UserAccount> getCacheDataInListForm() {
        List<UserAccount> listOfCacheData = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            listOfCacheData.add(temp.userAccount);
            temp = temp.next;
        }
        return listOfCacheData;
    }

    public void setCapacity(int newCapacity) {
        if (capacity > newCapacity) {
            clear();// Behavior can be modified to accommodate for decrease in cache size. For now, we'll
            // just clear the cache.
        } else {
            this.capacity = newCapacity;
        }
    }
}
