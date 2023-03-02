package com.atguigu.spring_security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class SpringSecurityApplicationTests {
    @Resource
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        String compact = Jwts.builder()
                .setId(UUID.randomUUID().toString().replaceAll("-", ""))
                .setIssuer("xu")
                .setIssuedAt(new Date())
                .setSubject("1")
                .signWith(SignatureAlgorithm.HS256, "jiayan")
                .compact();
        System.out.println(compact);
        Claims jiayan = Jwts.parser().setSigningKey("jiayan").parseClaimsJws(compact).getBody();
        System.out.println(jiayan);


    }
    @Test
    public void test(){
        redisTemplate.opsForValue().set("test","test");
        System.out.println(redisTemplate.opsForValue().get("test"));

//        redisTemplate.opsForValue().set("test","test");
    }

}
