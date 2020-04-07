package com.example.student.mq;

import com.alibaba.fastjson.JSON;
import com.example.student.domain.StuLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMqTest {

    @Autowired
    private SendMq sendMq;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        StuLog stuLog = new StuLog();
        stuLog.setId(1);
        stuLog.setUsername("测试");
        stuLog.setDesc("用来测试RabbitMQ");
        sendMq.send(JSON.toJSONString(stuLog));
    }
}