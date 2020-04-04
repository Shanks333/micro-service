package com.example.zuul.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.zuul.utils
 * @description
 * @date 2020/4/4 14:12
 */
@Component
public class JWTUtils {

    /**
     * 签名用的秘钥
     */
    private static final String SIGN_KEY = "123456";

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = (long)30 * 24 * 60 * 60 * 1000;

    /**
     * 用户登录成功后返回token
     * @param username 用户名
     * @return token字符串
     */
    public String createJWT(String username) {
        // 指定签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 载体
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        JwtBuilder builder = Jwts.builder()
                                .setClaims(claims)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                                .signWith(signatureAlgorithm, SIGN_KEY);
        return builder.compact();
    }

    /**
     * 解析token获得载体
     * @param token
     * @return
     */
    public Claims parseJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
