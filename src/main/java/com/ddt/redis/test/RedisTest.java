package com.ddt.redis.test;

import com.ddt.redis.RedisClient;
import com.ddt.utils.Databases;
import com.ddt.utils.DatabasesIndex;

import java.util.Set;

public class RedisTest {

    public static void main(String[] args) {
        testRedis();
    }

    public static void testRedis() {
        RedisClient client = new RedisClient("localhost", 6379);
//        client.flushDB();
//        client.set("key", "value");
        Set<String> sets = client.getAllKeys("*");
        System.out.println("redis keys size: " + sets.size());

        for (String key : sets) {
            System.out.println("keys: " + key);
        }


        System.out.println(DatabasesIndex.getInstance().getHPHostname(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPIp(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPPort(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPIdx(Databases.ACTIVE_PORT));
    }
}
