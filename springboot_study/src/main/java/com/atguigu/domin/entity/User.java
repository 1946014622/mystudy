package com.atguigu.domin.entity;

public class User {
    private String name;
    private Car car;

    public User() {
    }

    public User(String name,Car car) {
        this.name = name;
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
