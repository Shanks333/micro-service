package com.example.auth.service.impl;

import com.example.auth.domain.User;
import com.example.auth.mapper.UserMapper;
import com.example.auth.redis.UserRedis;
import com.example.auth.service.UserService;
import com.example.auth.utils.SelectorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.service.impl
 * @description
 * @date 2020/4/4 18:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRedis userRedis;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return SelectorUtil.selectCacheAndDb(() -> {
            System.out.println("从缓存中查找。。。。");
            return userRedis.selectUserByUsername(username); }, () -> {
            System.out.println("从数据库里查找。。。。");
            User user = userMapper.selectUserByUsername(username);
            // 将数据插入到缓存中去
            userRedis.insertUser(user);
            return user;
        });
    }
}
