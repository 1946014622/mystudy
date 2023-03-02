package com.atguigu.rabbit.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class QueueConfig {
    private static final String NORMAL_EXCHANGE = "normalExchange";
    private static final String DEAD_EXCHANGE = "deadExchange";
    private static final String NORMAL_QUEUE1 = "normalQueue1";
    private static final String NORMAL_QUEUE2 = "normalQueue2";
    private static final String DEAD_QUEUE = "deadQueue";

    /**
     * 正常交换机
     */
    @Bean(NORMAL_EXCHANGE)
    public DirectExchange directExchange(){
      return new DirectExchange(NORMAL_EXCHANGE);
    };
    /**
     * 正常队列1
     */
    @Bean(NORMAL_QUEUE1)
    public Queue normalQueue1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","dead");
        map.put("x-message-ttl",5000);
        return QueueBuilder.durable(NORMAL_QUEUE1).withArguments(map).build();
    }
    /**
     * 正常队列2
     */
    @Bean(NORMAL_QUEUE2)
    public Queue normalQueue2(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","dead");
        map.put("x-message-ttl",10000);
        return QueueBuilder
                .durable(NORMAL_QUEUE2)
                .ttl(1000)
                .deadLetterRoutingKey("dead")
                .deadLetterExchange(DEAD_EXCHANGE)
                .withArguments(map)
                .build();
    }
    /**
     * 正常队列1绑定正常交换机
     */
    @Bean()
    public Binding queue1BindingNormalExchange(@Qualifier(NORMAL_QUEUE1) Queue queue1
                            , @Qualifier(NORMAL_EXCHANGE)DirectExchange directExchange){
        return BindingBuilder.bind(queue1).to(directExchange).with("q1");
    }
    /**
     * 正常队列2绑定正常交换机
     */
    @Bean()
    public Binding queue2BindingNormalExchange(@Qualifier(NORMAL_QUEUE2) Queue queue2
            , @Qualifier(NORMAL_EXCHANGE)DirectExchange directExchange){
        return BindingBuilder.bind(queue2).to(directExchange).with("q2");
    }
    /**
     * 死信交换机
     */
    @Bean(DEAD_EXCHANGE)
    public DirectExchange deadExchange(){
        return new DirectExchange(DEAD_EXCHANGE);
    };
    /**
     * 死队列
     */
    @Bean(DEAD_QUEUE)
    public Queue deadQueue(){
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }
    /**
     * 死信队列绑定私信交换机
     */
    @Bean()
    public Binding deadQueueBindingDeadExchange(@Qualifier(DEAD_QUEUE) Queue deadQueue
            , @Qualifier(DEAD_EXCHANGE)DirectExchange deadExchange){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("dead");
    }
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return new DirectExchange("confirmExchange");
    }
    @Bean("confirmQueue")
    public Queue confirmQueue(){
      return QueueBuilder.durable("confirmQueue").build();
    };
    @Bean
    public Binding confirmQueueBinding(@Qualifier("confirmExchange")DirectExchange confirmExchange,
                                       @Qualifier("confirmQueue")Queue confirmQueue){
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with("key1");
    };

}
