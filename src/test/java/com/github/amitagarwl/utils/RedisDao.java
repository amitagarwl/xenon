package com.github.amitagarwl.utils;


import com.github.amitagarwl.config.RedisConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@AllArgsConstructor
public class RedisDao {

    private RedisConfig redisConfig;

    public void getData() {

        JedisPool jedisPool = new JedisPool(redisConfig.getRedisHost());
        try (final Jedis jedis = jedisPool.getResource()) {
            jedis.get(jedis.get(redisConfig.getRedisKey()));
        } catch (Exception e) {
            log.info("Error getting keys from redis - {} and error is {}", redisConfig.getRedisKey(), e.getMessage());
        }
    }

}
