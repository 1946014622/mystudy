package com.atguigu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HelloAspect {
    @Pointcut("@annotation(com.atguigu.annotation.HelloAnnotation)")
    public void pointcut(){
    }
    @Before("pointcut()")
    public void sayHello(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("调用了该方法");
    }
    @After("pointcut()")
    public void after(){
        System.out.println("结束了");
    }
}
