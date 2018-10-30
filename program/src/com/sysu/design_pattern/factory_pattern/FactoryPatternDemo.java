package com.sysu.design_pattern.factory_pattern;


/**
 * factory method pattern
 * 工厂方法模式，定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。
 * 工厂方法让类把实例化推迟到子类
 *
 * abstract factory pattern
 * 抽象工厂模式，抽象工厂定义了一个负责创建一组产品的接口，利用工厂方法实现
 */


//使用该工厂，通过传递类型信息来获取实体类的对象。
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        //调用 Circle 的 draw 方法
        shape1.draw();
        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        //调用 Rectangle 的 draw 方法
        shape2.draw();
        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");
        //调用 Square 的 draw 方法
        shape3.draw();
    }
}
