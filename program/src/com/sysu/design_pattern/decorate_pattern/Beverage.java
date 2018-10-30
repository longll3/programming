package com.sysu.design_pattern.decorate_pattern;

/**
 * 装饰者模式动态地将责任附件到对象上。
 * 在扩展功能方面，装饰者模式提供了比继承更有弹性的替代方案
 *
 * 装饰者和被装饰者必须是一样的类型
 */


/**
 * 一个咖啡店的订单系统
 */


/**
 * Beverage 饮料
 * 相当于抽象的component类
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {return description;}

    public abstract double cost();
}
