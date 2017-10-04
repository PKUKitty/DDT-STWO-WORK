package com.ddt.redis.test;

import com.ddt.redis.RedisClient;
import com.ddt.utils.Databases;
import com.ddt.utils.DatabasesIndex;

public class RedisTest {

    public static void main(String[] args) {
        testRedis();
    }

    public static void testRedis() {
        RedisClient client = new RedisClient("localhost", 6379);
        client.flushDB();
        client.set("key", "value");


        System.out.println(DatabasesIndex.getInstance().getHPHostname(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPIp(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPPort(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPIdx(Databases.ACTIVE_PORT));
    }
}
