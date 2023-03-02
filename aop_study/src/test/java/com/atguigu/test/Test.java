package com.atguigu.test;

import com.atguigu.calculatorLog.CalculatorLog;
import com.atguigu.calculatorLog.aop.LogAspect;
import com.atguigu.calculatorLog.impl.CalculatorLogImpl;
import com.atguigu.calculatorLog.proxy.CalculatorLogProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application-config.xml");
        CalculatorLog bean = ioc.getBean(CalculatorLog.class);
        bean.add(10,10,10,10);

    }
}
