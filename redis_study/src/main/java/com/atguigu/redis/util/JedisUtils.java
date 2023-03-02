package com.atguigu.redis.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisUtils {

    static JedisPool jedisPool = null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(10000));
        jedisPoolConfig.setMinIdle(0);
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.10.100",6379,0,null);

    }
    public static synchronized Jedis getJedis(){
        if (jedisPool != null) {
            return jedisPool.getResource();
        }else {
            System.out.println("未找到");
            return null;
        }
    }
}
