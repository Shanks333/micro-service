package com.example.student.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.mq
 * @description 发送消息
 * @date 2020/4/5 21:20
 */
@Component
public class SendMq {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     * @param msg 消息对象
     */
    public void send(String msg) {
        amqpTemplate.convertAndSend(msg);
    }

}
