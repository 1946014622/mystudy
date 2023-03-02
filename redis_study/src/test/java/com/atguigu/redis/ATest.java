package com.atguigu.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ATest {
    public static class AA{
        private BB bb;
        public void setBb(BB bb){
            this.bb = bb;
        }
    }
    public static class BB{
        private AA aa;
        public void setAa(){
            AA aa = null;
            this.aa = aa;
        }
    }

    public static void main(String[] args) {
        double v = 100.1 % 3.0;
        System.out.println(v);
        start();
    }
    public static void start(){
        double v = 4.0 * 2.0;
        AA aa = new AA();
        BB bb = new BB();
        aa.setBb(bb);
        bb = null;
        aa = null;
    }
    @Test
    public void test3() throws InterruptedException {
        Thread thread1 = new Thread(() -> {

            System.out.println(1);
        });
        Thread thread2 = new Thread(() -> {

            System.out.println(2);
//            try {
//                thread1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        Thread thread3 = new Thread(() -> {

            System.out.println(3);
        });
        thread1.start();
        thread2.start();
        thread3.start();


    }
}