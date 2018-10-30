package com.sysu.design_pattern.strategy_pattern;


/**
 * 策略模式：
 * 定义了算法族，相当于这里的 FlyBehavior flyBehavior 和 QuackBehavior quackBehavior; 分别封装起来，让它们之间可以相互替换，
 * 此模式让算法的变化独立于使用算法的客户。
 * 即FlyBehavior和QuackBehavior的变化独立于Duck
 *
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}

    public abstract void display();

    public void swim() {}

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
