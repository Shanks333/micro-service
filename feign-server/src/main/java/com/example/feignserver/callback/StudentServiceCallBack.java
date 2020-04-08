package com.example.feignserver.callback;

import com.example.feignserver.commons.ResponseData;
import com.example.feignserver.domain.StuInfo;
import com.example.feignserver.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.feignserver.callback
 * @description
 * @date 2020/4/7 20:47
 */
@Component
public class StudentServiceCallBack implements StudentService {

    @Override
    public ResponseData getStuInfosByPage(String page) {
        return new ResponseData()
                .setData(new ArrayList<>())
                .setStatus(500);
    }

    @Override
    public ResponseData changeStuInfo(StuInfo stuInfo) {
        return new ResponseData()
                .setData("服务出现异常")
                .setStatus(500);
    }
}
