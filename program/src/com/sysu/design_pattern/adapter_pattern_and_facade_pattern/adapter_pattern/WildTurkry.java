package com.sysu.design_pattern.adapter_pattern_and_facade_pattern.adapter_pattern;

public class WildTurkry implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short disatance");
    }
}
