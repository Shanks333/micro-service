package com.example.logserver.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.mapper
 * @description 操作学生信息的日志
 * @date 2020/4/5 15:09
 */
@Data
public class StuLog implements Serializable {

    private Integer id;

    private String username;

    private String desc;
}
