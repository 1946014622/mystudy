package com.atguigu.test;

import org.aspectj.weaver.ast.Var;

public class Test {
    public static void main(String[] args) {
        Son son = new Son("许郑郑");
        String name = son.getName();
        System.out.println(name);
    }

}
class Parent{
    private String name;

    public Parent(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Son extends Parent{
    public Son(String name) {
        super(name);
    }

}