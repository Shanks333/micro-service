package com.example.auth.controller;

import com.example.auth.commons.ResponseData;
import com.example.auth.domain.User;
import com.example.auth.redis.TokenRedis;
import com.example.auth.service.UserService;
import com.example.auth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;


/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.controller
 * @description
 * @date 2020/4/4 18:38
 */
@RestController
@Validated
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private TokenRedis tokenRedis;

    @PostMapping("/login")
    public ResponseData login(@NotBlank String username, @NotBlank String password) {
        User user = userService.getUserByUsername(username);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Password);
        if (!md5Password.equals(user.getPassword())) {
            return new ResponseData()
                    .setData("账号或密码不正确！")
                    .setStatus(402);
        }
        String token = jwtUtils.createJWT(username);
        tokenRedis.insertToken(username, token);
        return new ResponseData()
                .setData(token)
                .setStatus(200);
    }

    @PostMapping("/exit")
    public ResponseData exit(@NotBlank String username) {
        tokenRedis.deleteToken(username);
        return new ResponseData()
                .setData("退出成功！")
                .setStatus(200);
    }

    @PostMapping("/")
}
