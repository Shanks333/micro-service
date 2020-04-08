package com.example.feignserver.controller;

import com.example.feignserver.commons.ResponseData;
import com.example.feignserver.domain.StuInfo;
import com.example.feignserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.feignserver.controller
 * @description
 * @date 2020/4/7 20:40
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/stuinfos/page-{page}")
    public ResponseData getStudents(@PathVariable String page) {
        return studentService.getStuInfosByPage(page);
    }

    @PostMapping("/stuinfo")
    public ResponseData changeStuInfo(StuInfo stuInfo) {
        System.out.println(stuInfo);
        return studentService.changeStuInfo(stuInfo);
    }

}
