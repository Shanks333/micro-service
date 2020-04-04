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

    User selectUserByUsername(String username);
}
