/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.caching;

import com.ddt.utils.Serialize;
import redis.clients.jedis.Jedis;

import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public final class DbManager {

    private static Jedis jedisClient;

    private static boolean useRedisDB;

    private static Map<String, UserAccount> virtualDB;

    private static final String redisHost = "localhost";

    private DbManager() {

    }

    /**
     * Create DB
     */
    public static void createVirtualDb() {
        useRedisDB = false;
        virtualDB = new HashMap<>();
    }

    /**
     * Connect to DB
     */
    public static void connect() throws ParseException {
        useRedisDB = true;
        jedisClient = new Jedis(redisHost);
    }


    /**
     * Read user account from DB
     */
    public static UserAccount readFromDb(String userId) {
        if (!useRedisDB) {
            if (virtualDB.containsKey(userId)) {
                return virtualDB.get(userId);
            }
            return null;
        }

        if (jedisClient != null) {
            return (UserAccount) Serialize.byte2Object(jedisClient.get(userId.getBytes()));
        }
        return null;
    }

    /**
     * Read user account from DB
     */
    public static UserAccount readFromDb(byte[] userId) {
        if (!useRedisDB) {
            if (virtualDB.containsKey(new String(userId))) {
                return virtualDB.get(new String(userId));
            }
            return null;
        }

        if (jedisClient != null) {
            return (UserAccount) Serialize.byte2Object(jedisClient.get(userId));
        }
        return null;
    }


    /**
     * Write user account to DB
     */
    public static void writeToDb(UserAccount userAccount) {
        if (!useRedisDB) {
            virtualDB.put(userAccount.getUserId(), userAccount);
            return;
        }

        if (jedisClient != null) {
            jedisClient.set(userAccount.getUserId().getBytes(), Serialize.object2Byte(userAccount));
        }
    }

    /**
     * Update DB
     */
    public static void updateDb(UserAccount userAccount) {
        if (!useRedisDB) {
            virtualDB.put(userAccount.getUserId(), userAccount);
            return;
        }

        if (jedisClient != null) {
            jedisClient.set(userAccount.getUserId().getBytes(), Serialize.object2Byte(userAccount));
        }
    }

    /**
     * Insert data into DB if it does not exist. Else, update it.
     */
    public static void upsertDb(UserAccount userAccount) {
        if (!useRedisDB) {
            virtualDB.put(userAccount.getUserId(), userAccount);
            return;
        }
        
        if (jedisClient != null) {
            jedisClient.set(userAccount.getUserId().getBytes(), Serialize.object2Byte(userAccount));
        }
    }

}
