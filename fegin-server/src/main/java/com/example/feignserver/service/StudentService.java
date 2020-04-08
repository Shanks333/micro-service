package com.example.feignserver.service;

import com.example.feignserver.callback.StudentServiceCallBack;
import com.example.feignserver.commons.ResponseData;
import com.example.feignserver.domain.StuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.feignserver.service
 * @description
 * @date 2020/4/7
 */
@FeignClient(name = "student-server", fallback = StudentServiceCallBack.class)
public interface StudentService {

    @GetMapping("/stuinfos/page-{page}")
    ResponseData getStuInfosByPage(@PathVariable String page);

    @PostMapping("/stuinfo")
    ResponseData changeStuInfo(@RequestBody StuInfo stuInfo);
}
