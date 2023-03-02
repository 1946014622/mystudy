package com.atguigu.calculatorLog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(public int com.atguigu.calculatorLog.CalculatorLog.add(int ...))")

    public void pointCut(){

    };
    @Before("pointCut()")
    @Order(1)
    public void beforeLog1(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString((int[]) joinPoint.getArgs()[0]);
        System.out.println("Logger-->前置通知1，方法名："+methodName+"，参数："+args);
    }
    @Before("pointCut()")
    @Order(2)
    public void beforeLog(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString((int[]) joinPoint.getArgs()[0]);
        System.out.println("Logger-->前置通知，方法名："+methodName+"，参数："+args);
    }

    @After("execution(public int com.atguigu.calculatorLog.CalculatorLog.add(..))")
    public void afterLog(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString((int[]) joinPoint.getArgs()[0]);
        System.out.println("Logger-->后置通知，方法名："+methodName+"，参数："+args);
    }
    @AfterReturning("execution(public int com.atguigu.calculatorLog.CalculatorLog.add(..))")

    public void AfterReturnLog(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString((int[]) joinPoint.getArgs()[0]);
        System.out.println("Logger-->返回通知，方法名："+methodName+"，参数："+args);
    }
    @AfterThrowing(value = "execution(public int com.atguigu.calculatorLog.CalculatorLog.add(..))",throwing = "ex")
    public void AfterThrowLog(JoinPoint joinPoint,Throwable ex){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString((int[]) joinPoint.getArgs()[0]);
        System.out.println("Logger-->异常通知，方法名："+methodName+"，参数："+args+",执行失败：\n"+ex.getCause());
    }
    @Order(1)
    @Around("execution(public int com.atguigu.calculatorLog.impl.CalculatorLogImpl.add(..))")
    public Object AfterThrowLog(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString((int[]) joinPoint.getArgs()[0]);
        Object result = null;
        try {
            System.out.println("Logger-->环绕通知，方法前执行，方法名："+methodName+"，参数："+args);
            result = joinPoint.proceed();
            System.out.println("Logger-->环绕通知，方法后执行，方法名："+methodName+"，参数："+args);
        } catch (Throwable e) {
            System.out.println("Logger-->环绕通知，异常，方法名："+methodName+",执行失败：\n"+e.getCause());
        }
        System.out.println("Logger-->环绕通知，方法完毕，方法名："+methodName+"，参数："+args);
        return result;
    }
}
