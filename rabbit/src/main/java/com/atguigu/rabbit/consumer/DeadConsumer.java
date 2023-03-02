package com.atguigu.rabbit.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class DeadConsumer {
    @RabbitListener(queues = "deadQueue")
    public void getMessage(Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        channel.basicAck(tag,false);
//        channel.basicReject(tag,true);
        log.info("当前时间：{},收到死信队列信息{}", new Date().toString(), new String(message.getBody()));
    }
    @RabbitListener(queues = "confirmQueue")
    public void confirmQueue(Message message, Channel channel){

        log.info("当前时间：{},收到确认队列信息{}", new Date().toString(), new String(message.getBody()));
    }
}
