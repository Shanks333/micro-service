package com.example.logserver.mapper;

import com.example.logserver.domain.StuLog;
import org.springframework.stereotype.Repository;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.logserver.mapper
 * @description
 * @date 2020/4/7
 */
@Repository
public interface StuLogMapper {

    Integer insertStuLog(StuLog stuLog);
}
