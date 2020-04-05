package com.example.student.commons.web;

import com.example.student.commons.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.commons.web
 * @description 统一异常处理
 * @date 2020/4/4 19:43
 */
//@ControllerAdvice
public class ErrorHandlerException {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseData errorLogin() {
        return new ResponseData()
                .setData("账号或密码不正确！")
                .setStatus(402);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseData errorHandler() {
        return new ResponseData()
                .setData("服务器出现异常")
                .setStatus(500);
    }
}
