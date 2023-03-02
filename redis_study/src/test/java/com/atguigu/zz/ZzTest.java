package com.atguigu.zz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest(classes = ZzTest.class)
public class ZzTest {
    @Test
    public void test(){
        String str = "-a5464564@qq.com";
        String zz = "^[0-9a-z_,_-]{3,}@qq.com$";
        System.out.println(str.matches(zz));
    }
    @Test
    public void test2(){
        String str = "(\\d{2})([a-z]{2,3})";
        String line = "33aa-32sdy-29ssc";
        Pattern compile = Pattern.compile(str);
        Matcher matcher = compile.matcher(line);
        if (matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }
    @Test
    public void test3(){
        String str = "rrrrrtwe";
        String pattern = ".*r{1,5}?.*";
        System.out.println(str.matches(pattern));
        String s = str.replaceAll(pattern,"o");
        System.out.println(s);

    }
}
