package com.example.zuul.controller;

import com.example.zuul.commons.ResponseData;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.zuul.controller
 * @description 统一异常处理
 * @date 2020/4/4 19:38
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseData errorHandler() {
        return new ResponseData()
                .setData("服务器出现异常")
                .setStatus(500);
    }
}
