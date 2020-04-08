package com.example.ribbonserver.controller;

import com.example.ribbonserver.commons.ResponseData;
import com.example.ribbonserver.domain.StuInfo;
import com.example.ribbonserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.ribbonserver.controller
 * @description
 * @date 2020/4/8 12:13
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/stuinfos/page-{page}")
    public ResponseData getStuInfos(@PathVariable String page) {
        return studentService.getStuInfosByPage(page);
    }

    @PostMapping("/stuinfo")
    public ResponseData changeStuInfo(StuInfo stuInfo) {
        return studentService.changeStuInfo(stuInfo);
    }
}
