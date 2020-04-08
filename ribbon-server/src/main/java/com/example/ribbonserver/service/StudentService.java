package com.example.ribbonserver.service;

import com.example.ribbonserver.commons.ResponseData;
import com.example.ribbonserver.domain.StuInfo;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.ribbonserver.service
 * @description
 * @date 2020/4/8 12:15
 */
public interface StudentService {

    ResponseData getStuInfosByPage(String page);

    ResponseData changeStuInfo(StuInfo stuInfo);
}
