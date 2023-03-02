package com.atguigu.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.ConnectionPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class RedisStudyApplicationTests {
    @Test
    public void jedisClusterTest(){

        HashSet<HostAndPort> hostAndPorts = new HashSet<>();
        hostAndPorts.add((new HostAndPort("192.168.10.100",6379)));
//        hostAndPorts.add((new HostAndPort("192.168.10.100",6380)));
//        hostAndPorts.add((new HostAndPort("192.168.10.100",6381)));
//        hostAndPorts.add((new HostAndPort("192.168.10.100",6389)));
//        hostAndPorts.add((new HostAndPort("192.168.10.100",6390)));
//        hostAndPorts.add((new HostAndPort("192.168.10.100",6391)));
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts);
        jedisCluster.mset("1{1}","1","2{1}","2");
        System.out.println(jedisCluster.get("s"));
//        jedisCluster.set("1","2");
    }

    public static void main(String[] args) throws IOException {
        E e = new E(1);
        System.out.println(e.i);
        Integer i1 = 59;
        int i2 = 59;
        Integer i3 = Integer.valueOf(59);
        Integer i4 = new Integer(59);
        System.out.println(i1 == i2);//true
        System.out.println(i1 == i3);//true
        System.out.println(i1 == i4);//false
        System.out.println(i2 == i3);//true
        System.out.println(i2 == i4);
        System.out.println(i3 == i4);
    }
    public static void q1(){
        char s = 'g';
        String s1 = String.valueOf(s);
        System.out.println(s1);
    }
    public static void q2(){

    }
    public static String name = "";
    public String hobby = "";
}
class A implements C{
    public float getNum(){
        return 3.0f;
    }
}
class B extends A{
    public void getNum(double b){
        A a = new A();
        a.test();
    }
}
interface C {
     int i = 0;
     default void test(){
         System.out.println("你好");
     }
}
abstract class D{
    int i;
    public D(){

    }
    public D(int i){
        this.i = i;
    }
}
class E extends D{
    public E(){}
    public E(int i){
        super(i);
    }
}
