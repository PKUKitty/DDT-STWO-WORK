package com.ddt.redis;

public enum RedisStatusCode {
    REDIS_OK("REDIS_OK");

    private final String name;

    RedisStatusCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
