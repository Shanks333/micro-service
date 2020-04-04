package com.example.auth.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.domain
 * @description 用户表的实体类
 * @date 2020/4/4 18:27
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;
}
