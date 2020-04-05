package com.example.student.mq;

import com.example.student.domain.StuLog;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.mq
 * @description
 * @date 2020/4/5 21:46
 */
@Component
public class ReceiveMq {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "test"), exchange = @Exchange("test.direct"), key = "abc"))
    public void processMq(StuLog msg) {
        System.out.println(msg);
    }
}
