package com.atguigu.rabbit;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@Slf4j
class RabbitApplicationTests {
    private final static String QUEUE_NAME = "hello";
    @Test
    void sendMessage() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");//设置与服务器连接的地址
        try {
            Connection connection = connectionFactory.newConnection();//获取连接
            Channel channel = connection.createChannel();//创建信道
            channel.queueDeclare("hello2", true, false, false, null);//声明一个队列
            String message = "hello,my first test for rabbitMq....2";
            channel.basicPublish("","hello2",MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());//MessageProperties.PERSISTENT_TEXT_PLAIN 设置消息持久
            System.out.println("[send]: "+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void getMessage(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Channel channel = connectionFactory.newConnection().createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getWork(){
        System.out.println("进入了getWork方法");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Channel channel = connectionFactory.newConnection().createChannel();
            DeliverCallback deliverCallback = (consumerTag,delivery)->{
              String message = new String(delivery.getBody(),"UTF-8");
//                doWork(message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),true);//通知rabbitmq成功接收到消息
                System.out.println(message);
            };
            channel.basicConsume(QUEUE_NAME,false,deliverCallback,s->{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void doWork(String message){
        Assert.notNull(message,"短信不能为空");
        for (char c : message.toCharArray()) {
            if (c == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    void exchange() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Channel channel = factory.newConnection().createChannel();
        channel.queueDeclare("hello3",true,false,false,null);
        channel.exchangeDeclare("firstExchange","direct");//创建一个交换机，类型为将接收的消息广播到它已知的所有队列
        String message = "this is green queue";
//        String queueName = channel.queueDeclare().getQueue();
//        System.out.println("随机消息队列的名称："+queueName);
//        channel.queueBind(queueName,"firstExchange","");
        channel.queueBind("hello","firstExchange","green");
        channel.queueBind("hello2","firstExchange","black");
        channel.queueBind("hello3","firstExchange","red");
        channel.basicPublish("firstExchange","blue.*",null,message.getBytes());

    }
    @Test
    void exchangeWorker() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Channel channel = factory.newConnection().createChannel();
        channel.exchangeDeclare("firstExchange","direct");//创建一个交换机，类型为将接收的消息广播到它已知的所有队列
        channel.queueDeclare("hello4",true,false,false,null);
        channel.queueDeclare("hello5",true,false,false,null);
        channel.queueDeclare("hello6",true,false,false,null);
//        channel.queueBind("hello","firstExchange","");
//        channel.queueBind("hello2","firstExchange","");
//        channel.queueBind("hello3","firstExchange","1");
        channel.queueBind("hello4","firstExchange","blue.1");
        channel.queueBind("hello5","firstExchange","blue.2.2");
        channel.queueBind("hello6","firstExchange","blue.3.3");
        DeliverCallback deliverCallback = (consumerTag,delivery)->{
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("接收的消息："+message);
        };
        channel.basicConsume("hello4",true,deliverCallback,consumerTag -> {});
    }
}
