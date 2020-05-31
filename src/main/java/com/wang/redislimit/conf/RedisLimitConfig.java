package com.wang.redislimit.conf;

import com.wang.limit.fuse.RedisCurrentLimit;
import com.wang.limit.fuse.RedisCurrentLimitFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@ComponentScan(value = "com.wang.limit.intercept")
@Configuration
public class RedisLimitConfig {

    @Autowired
    private JedisPool jedisPool;

    @Bean
    public JedisPool buildJedisPool() {
        try {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            //DEMO 没有设置配置文件
            poolConfig.setMaxTotal(1000);
            poolConfig.setMaxIdle(32);
            poolConfig.setMaxWaitMillis(100 * 1000);
            poolConfig.setTestOnBorrow(true);

            jedisPool = new JedisPool(poolConfig, ridisIP, redis端口号);
            return jedisPool;
        } catch (Exception e) {
            throw new RuntimeException("JedisPool初始化失败:" + e.getMessage());
        }
    }


    @Bean
    public RedisCurrentLimit buildRedisCurrentLimit() {
        int limit = 1;
        RedisCurrentLimitFactory redisCurrentLimitFactory = new RedisCurrentLimitFactory();
        try {
            return redisCurrentLimitFactory.standAloneInstance(limit, jedisPool);
        } catch (Exception e) {
            throw new RuntimeException("redis限流初始化失败" + e.getMessage());
        }
    }
}
