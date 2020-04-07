package com.example.logserver.service;

import com.example.logserver.domain.StuLog;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.logserver.service
 * @description
 * @date 2020/4/7
 */
public interface StuLogService {

    /**
     * 插入学生信息操作日志
     * @param stuLog
     * @return
     */
    Boolean addStuLog(StuLog stuLog);
}
