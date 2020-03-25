package com.licslan.array;

public class Person {
    private String name;
    private int age;
    public Person(String name,int age){
        this.name=name;
        this.age=age;
    }
    @Override
    public String toString(){
        // %s 字符串  %d 数字
       return String.format("Person(name:%s,age:%d)",name,age);
    }
}
