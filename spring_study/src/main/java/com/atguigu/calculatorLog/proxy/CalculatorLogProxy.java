package com.atguigu.calculatorLog.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorLogProxy {
    private Object proxy;

    public CalculatorLogProxy(Object proxy) {
        this.proxy = proxy;
    }

    public Object getProxy(){
        ClassLoader classLoader = proxy.getClass().getClassLoader();
        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            int result = 0;
            int [] params = (int[]) args[0];
            System.out.println("[动态代理][日志] " + method.getName() + "，参数：" +  Arrays.toString(params));
            try {
                result = (int) method.invoke(CalculatorLogProxy.this.proxy, args);

            } catch (Exception e) {
                System.out.println("[动态代理][日志] " + method.getName() + "，参数：" + Arrays.toString(params) + "，执行失败:\n" + e.getCause());
            }
            System.out.println("[动态代理][日志] " + method.getName() + "，参数：" + Arrays.toString(params) + "，执行结果：" + result);
            return result;
        };
        return Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }
}
