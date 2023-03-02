package com.atguigu.rabbit.controller;

import com.atguigu.rabbit.config.Callback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RequestMapping("sendMessage")
@RestController
public class SendController implements RabbitTemplate.ReturnsCallback,RabbitTemplate.ConfirmCallback{
    @Resource
    private RabbitTemplate rabbitTemplate;
//    @Resource
//    private Callback callback;
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(this);
    }
    @GetMapping("test1")
    public void sendMessage(String message){
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("normalExchange", "q1", "消息来自 ttl 为 5S 的队列: "+message);
        rabbitTemplate.convertAndSend("normalExchange", "q2", "消息来自 ttl 为 10S 的队列: "+message,correlationData->{
            correlationData.getMessageProperties().setPriority(10);
            return correlationData;
        });
    }
    @GetMapping("confirmTest")
    public void confirmTest(String message){
        CorrelationData correlationData1 = new CorrelationData("1");
        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend("confirmExchange","key1",message+"1",correlationData1);
        rabbitTemplate.convertAndSend("confirmExchange","key2",message+"2",correlationData2);
        log.info("发送消息内容:{}",message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String id = correlationData==null?"":correlationData.getId();
        if (b) {
            log.info("消费者接收到id为:{} 的消息",id);
        } else {
            log.info("消费者没有接收到id为:{} 的消息",id);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息:{}被服务器退回，退回原因:{}, 交换机是:{}, 路由 key:{}",
                new String(returnedMessage.getMessage().getBody()),returnedMessage.getReplyText(),
                returnedMessage.getExchange(), returnedMessage.getRoutingKey());
    }
}
