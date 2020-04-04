package com.example.auth.redis;

import com.example.auth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.redis
 * @description 用户相关的redis操作
 * @date 2020/4/4 18:37
 */
@Component
public class UserRedis {

    public static final String PRE = "user.";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    public User selectUserByUsername(String username) {
        return (User) redisTemplate.opsForValue().get(PRE + username);
    }

    /**
     * 插入用户信息
     * @param user
     */
    public void insertUser(User user) {
        redisTemplate.opsForValue().set(PRE + user.getUsername(), user);
    }
}
