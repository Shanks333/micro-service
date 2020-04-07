package com.example.logserver.mq;

import com.alibaba.fastjson.JSON;
import com.example.logserver.domain.StuLog;
import com.example.logserver.service.StuLogService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.logserver.mq
 * @description 接收RabbitMQ里的消息
 * @date 2020/4/7 17:00
 */
@Component
public class ReceiveMq {

    @Autowired
    private StuLogService stuLogService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("student-log"), exchange = @Exchange("student.direct"), key = "student-log"))
    public void receiveStuLog (String msg) {
        StuLog stuLog = JSON.parseObject(msg, StuLog.class);
        stuLogService.addStuLog(stuLog);
    }
}
