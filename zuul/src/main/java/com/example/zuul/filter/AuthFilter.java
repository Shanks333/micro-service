package com.example.zuul.filter;

import com.example.zuul.commons.UrlConfig;
import com.example.zuul.utils.JWTUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.zuul.filter
 * @description
 * @date 2020/4/4 14:35
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private  UrlConfig urlConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${custom.redis-token}")
    private String redisToken;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        // 验证ip是否可访问
        if (!isAnnoIp(request)) {
            responseErrorMessage(response);
            return null;
        }

        // 验证是否要鉴权的路径
        for (String url : urlConfig.getAnnoUrls()) {
            if (url.equals(request.getRequestURI())) {
                return null;
            }
        }

        // 验证token
        String token = request.getHeader("Authorization");
        if (token == null) {
            responseErrorMessage(response);
            return null;
        }
        // 验证token的有效性
        Claims claims = null;
        try {
            claims = jwtUtils.parseJWT(token);
        } catch (Exception e) {
            responseErrorMessage(response);
            return null;
        }
        String username = claims.get("username").toString();
        if (username == null || redisTemplate.opsForHash().get(redisToken, username) == null) {
            responseErrorMessage(response);
            return null;
        }
        context.addZuulRequestHeader("username", username);
        return null;
    }

    /**
     * 响应错误信息给前端
     */
    private void responseErrorMessage(HttpServletResponse response) {
        RequestContext.getCurrentContext().setSendZuulResponse(false);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write("\"{\"data\":\"没有权限访问！\",\"status\":\"401\"}\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否需要放行的url
     * @return
     */
    private boolean isAnnoIp( HttpServletRequest request) {

        String visitorIp = getVisitorIp(request);
        for (String ip : urlConfig.getAnnoIps()) {
            if (ip.equals(visitorIp)) {
                return true;
            }
        }
        return false;
    }

    /***
     * 获得访问者的ip
     * @return
     */
    public static String getVisitorIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ipAddress = null;
        if (realIp == null) {
            if (forwarded == null) {
                ipAddress = remoteAddr;
            } else {
                ipAddress = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ipAddress = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ipAddress = realIp + "/" + forwarded;
            }
        }
        return ipAddress;
    }
}
