package com.example.logserver.mq;

import com.example.logserver.domain.StuLog;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(bindings = @QueueBinding(exchange = "student"))
    public void receiveStuLog (StuLog log) {
        System.out.println(log);
    }
}
