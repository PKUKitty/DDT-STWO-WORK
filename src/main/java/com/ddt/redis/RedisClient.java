package com.ddt.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisClient {

    private Jedis jedis;

    private JedisPool jedisPool;

    private ShardedJedis shardedJedis;

    private ShardedJedisPool shardedJedisPool;

    private String host;

    private int port;


    public RedisClient(final String host, final int port) {
        this.host = host;
        this.port = port;
        initialPool();
        initialSharedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }


    private void initialPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config, host, port);
    }

    private void initialSharedPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);

        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(host, port, "master"));

        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public String set(final String key, final String value){
        return shardedJedis.set(key, value);
    }

    public String flushDB(){
        return jedis.flushDB();
    }

    public Set<String> getAllKeys(final String pattern){
        return jedis.keys(pattern);
    }

    public void pipedInsert(){
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("key","value");
    }

    public String switchDB(int dbIndex) {
        return jedis.select(dbIndex);
    }
}
