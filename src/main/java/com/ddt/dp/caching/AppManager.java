/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.caching;

import java.text.ParseException;

/**
 * AppManager helps to bridge the gap in communication between the main class and the application's
 * back-end. DB connection is initialized through this class. The chosen caching strategy/policy is
 * also initialized here. Before the cache can be used, the size of the cache has to be set.
 * Depending on the chosen caching policy, AppManager will call the appropriate function in the
 * CacheStore class.
 */
public final class AppManager {
    private static CachingPolicy cachingPolicy;

    private AppManager() {
    }

    /**
     * Developer/Tester is able to choose whether the application should use Redis DB as its underlying
     * data storage or a simple Java data structure to (temporarily) store the data/objects during
     * runtime.
     */
    public static void initDb(boolean useRedisDb) {
        if (useRedisDb) {
            try {
                DbManager.connect();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            DbManager.createVirtualDb();
        }
    }

    /**
     * Initialize caching policy
     */
    public static void initCachingPolicy(CachingPolicy policy) {
        cachingPolicy = policy;
        if (cachingPolicy == CachingPolicy.BEHIND) {
            Runtime.getRuntime().addShutdownHook(new Thread(CacheStore::flushCache));
        }
        CacheStore.clearCache();
    }

    public static void initCacheCapacity(int capacity) {
        CacheStore.initCapacity(capacity);
    }

    /**
     * Find user account
     */
    public static UserAccount find(String userId) {
        if (cachingPolicy == CachingPolicy.THROUGH || cachingPolicy == CachingPolicy.AROUND) {
            return CacheStore.readThrough(userId);
        } else if (cachingPolicy == CachingPolicy.BEHIND) {
            return CacheStore.readThroughWithWriteBackPolicy(userId);
        } else if (cachingPolicy == CachingPolicy.ASIDE) {
            return findAside(userId);
        }
        return null;
    }

    /**
     * Save user account
     */
    public static void save(UserAccount userAccount) {
        if (cachingPolicy == CachingPolicy.THROUGH) {
            CacheStore.writeThrough(userAccount);
        } else if (cachingPolicy == CachingPolicy.AROUND) {
            CacheStore.writeAround(userAccount);
        } else if (cachingPolicy == CachingPolicy.BEHIND) {
            CacheStore.writeBehind(userAccount);
        } else if (cachingPolicy == CachingPolicy.ASIDE) {
            saveAside(userAccount);
        }
    }

    public static String printCacheContent() {
        return CacheStore.print();
    }

    /**
     * Cache-Aside save user account helper
     */
    private static void saveAside(UserAccount userAccount) {
        DbManager.updateDb(userAccount);
        CacheStore.invalidate(userAccount.getUserId());
    }

    /**
     * Cache-Aside find user account helper
     */
    private static UserAccount findAside(String userId) {
        UserAccount userAccount = CacheStore.get(userId);
        if (userAccount != null) {
            return userAccount;
        }
        userAccount = DbManager.readFromDb(userId);
        if (userAccount != null) {
            CacheStore.set(userId, userAccount);
        }
        return userAccount;
    }
}
