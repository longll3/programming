package com.sysu.design_pattern.factory_pattern;

//实现接口的类
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
