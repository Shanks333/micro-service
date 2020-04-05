package com.example.student.domain;

import lombok.Data;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.mapper
 * @description 学生信息实体类
 * @date 2020/4/5 15:05
 */
@Data
public class StuInfo {

    private Integer id;

    private String stuId;

    private String stuName;

    private Integer stuSex;

    private Integer stuAge;

    private String stuPlace;
}
