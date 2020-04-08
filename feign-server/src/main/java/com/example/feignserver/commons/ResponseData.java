package com.example.feignserver.commons;

import lombok.Getter;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.commons
 * @description 向前端响应的数据
 * @date 2020/4/4 18:27
 */
@Getter
public class ResponseData {

    private Object data;

    private Integer status;

    public ResponseData setData(Object data) {
        this.data = data;
        return this;
    }

    public ResponseData setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
