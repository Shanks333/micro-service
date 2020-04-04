package com.example.auth.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.redis
 * @description
 * @date 2020/4/4 21:28
 */
@Component
public class TokenRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${custom.redis-token}")
    private String redisToken;

    /**
     * 向缓存中插入token
     * @param token
     * @param username
     */
    public void insertToken(String username, String token) {
        redisTemplate.opsForHash().put(redisToken, username, token);
    }

    /**
     * 向缓存中删除token
     * @param username
     */
    public void deleteToken(String username) {
        redisTemplate.opsForHash().delete(redisToken, username);
    }
}
