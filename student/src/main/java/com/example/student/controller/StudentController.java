package com.example.student.controller;

import com.example.student.commons.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.controller
 * @description
 * @date 2020/4/5 15:22
 */
@RestController
public class StudentController {

    @GetMapping("/students/{pageNumber}")
    public ResponseData getStudents(@PathVariable String page) {
        System.out.println(page);
        return new ResponseData();
    }
}
