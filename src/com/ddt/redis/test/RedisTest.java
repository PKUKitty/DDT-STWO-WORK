package com.ddt.redis.test;

import com.ddt.utils.Databases;
import com.ddt.utils.DatabasesIndex;

public class RedisTest {

    public static void main(String[] args) {
        testRedis();
    }

    public static void testRedis() {
//        RedisClient client = new RedisClient("127.0.0.1", 6379);
//        client.flushDB();

        System.out.println(DatabasesIndex.getInstance().getHPHostname(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPIp(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPPort(Databases.ACTIVE_PORT));
        System.out.println(DatabasesIndex.getInstance().getHPIdx(Databases.ACTIVE_PORT));
    }
}
