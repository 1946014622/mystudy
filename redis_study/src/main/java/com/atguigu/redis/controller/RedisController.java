package com.atguigu.redis.controller;

import com.atguigu.redis.util.JedisUtils;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("redis")
@RestController
public class RedisController {

    private static Integer id = 0;
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.100",6379);
        jedis.set("test","test");
        System.out.println(jedis.get("test"));
    }
    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("test")
    public Integer test(Integer msg){
        redisTemplate.multi();
        redisTemplate.exec();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("msg",msg);
        msg = (Integer) valueOperations.get("msg");
        return msg;
    }

    @GetMapping("seckil")
    public void test(){
//        Jedis jedis = new Jedis("192.168.10.100",6379);
        Jedis jedis = JedisUtils.getJedis();
        jedis.watch("good");
        String good = jedis.get("good");
        if (Integer.parseInt(good) <= 0) {
            System.err.println("已秒光!!!!!");
            jedis.close();
            return;
        }
        Transaction multi = jedis.multi();
        multi.decr("good");

        List<Object> exec = multi.exec();
        if (exec != null && exec.size()>0) {
            System.out.println("秒杀成功!!!!!");
            jedis.close();
            return;
        }
        System.out.println("秒杀失败！！！！！");
        jedis.close();
    }
    @GetMapping("lock")
    public void lock(){
        long l = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();

        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid,1500,TimeUnit.MILLISECONDS);
        if (lock) {
            Object num = redisTemplate.opsForValue().get("num");

            if (num != null) {
                Integer count = Integer.parseInt(num+"");
                if (count == 2000 && id == 0) {
                    id ++;
                    int size = count/0;
                }
                redisTemplate.opsForValue().set("num",++count);
                if (uuid.equals((String)(redisTemplate.opsForValue().get("lock")))) {
                    redisTemplate.delete("lock");
                }

            }
        }else {
            try {
                Thread.sleep(100);
                lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

    }
    @GetMapping("nginx")
    public void nginx(){
        System.out.println("进入了当前方法");
    }
}
