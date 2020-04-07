package com.example.logserver.service.impl;

import com.example.logserver.mapper.StuLogMapper;
import com.example.logserver.service.StuLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.logserver.service.impl
 * @description
 * @date 2020/4/7 16:59
 */
@Service
public class StuLogServiceImpl implements StuLogService {

    @Autowired
    private StuLogMapper stuLogMapper;
}
