package com.example.ribbonserver.service.impl;

import com.example.ribbonserver.commons.ResponseData;
import com.example.ribbonserver.domain.StuInfo;
import com.example.ribbonserver.service.StudentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.ribbonserver.service.impl
 * @description
 * @date 2020/4/8 12:15
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "errorResult")
    public ResponseData getStuInfosByPage(String page) {
        return restTemplate.getForObject("http://student-server/stuinfos/page-" + page, ResponseData.class);
    }

    @Override
    public ResponseData changeStuInfo(StuInfo stuInfo) {
        return restTemplate.postForObject("http://student-server/stuinfo", stuInfo, ResponseData.class);
    }

    public ResponseData errorResult(String page) {
        return new ResponseData()
                .setData(new ArrayList<>())
                .setStatus(500);
    }
}
