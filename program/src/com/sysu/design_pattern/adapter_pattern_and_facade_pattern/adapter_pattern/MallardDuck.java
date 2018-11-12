package com.sysu.design_pattern.adapter_pattern_and_facade_pattern.adapter_pattern;

public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("i'm flying");
    }
}
