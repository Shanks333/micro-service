package com.example.auth.mapper;

import com.example.auth.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.mapper
 * @description
 * @date 2020/4/4
 */
@Repository
public interface UserMapper {

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer updateUserPassword(User user);
}
