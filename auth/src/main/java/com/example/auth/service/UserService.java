package com.example.auth.service;

import com.example.auth.domain.User;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.service
 * @description
 * @date 2020/4/4
 */
public interface UserService {

    /***
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(String username);

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    Integer changePasswordByUser(User user);
}
