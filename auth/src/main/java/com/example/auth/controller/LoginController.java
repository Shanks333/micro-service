package com.example.auth.controller;

import com.example.auth.commons.ResponseData;
import com.example.auth.domain.User;
import com.example.auth.redis.TokenRedis;
import com.example.auth.redis.UserRedis;
import com.example.auth.service.UserService;
import com.example.auth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRedis userRedis;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private TokenRedis tokenRedis;

    /**
     * 登录系统
     * @param username
     * @param password
     * @return
     */
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

    /**
     * 退出系统
     * @param username
     * @return
     */
    @PostMapping("/exit")
    public ResponseData exit(@NotBlank String username) {
        tokenRedis.deleteToken(username);
        return new ResponseData()
                .setData("退出成功！")
                .setStatus(200);
    }

    /**
     * 更新密码
     * @param user
     * @param newPassword
     * @return
     */
    @PostMapping("/update-password")
    public ResponseData exit(User user, String newPassword) {
        User oldUser = userService.getUserByUsername(user.getUsername());
        if (!oldUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
            return new ResponseData()
                    .setData("旧密码输入错误！")
                    .setStatus(403);
        }
        if (!userRedis.delUserByUsername(user.getUsername())) {
            return new ResponseData()
                    .setData("修改密码失败！")
                    .setStatus(403);
        }
        // 更新数据库密码
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userService.changePasswordByUser(user);
        // 将token从缓存中删除
        tokenRedis.deleteToken(user.getUsername());
        return new ResponseData()
                .setData("密码修改成功！")
                .setStatus(200);
    }
}
