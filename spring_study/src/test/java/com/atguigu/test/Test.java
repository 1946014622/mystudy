package com.atguigu.test;

import com.atguigu.calculatorLog.CalculatorLog;
import com.atguigu.calculatorLog.impl.CalculatorLogImpl;
import com.atguigu.calculatorLog.proxy.CalculatorLogProxy;
import com.atguigu.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application-config.xml");
        UserController bean = ioc.getBean(UserController.class);
        bean.sayHello();
    }
    @org.junit.Test
    public void test1(){
        CalculatorLogProxy calculatorLogProxy = new CalculatorLogProxy(new CalculatorLogImpl());
        CalculatorLog proxy = (CalculatorLog) calculatorLogProxy.getProxy();
        proxy.add(1,2,3,4,5);
    }
}
