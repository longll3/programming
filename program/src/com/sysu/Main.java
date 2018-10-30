package com.sysu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        B b = new B();
        System.out.println(B.assignID());
    }





}

class A {
    private String a = "显式域初始化";
    private int id = assignID(); //初始值可以不限于常量

    //初始化块1
    {
        System.out.println("初始化块1");
    }

    public A() {
        System.out.println("普通构造器");
        System.out.println(a);
        a = "普通构造器之后的a的值";
        System.out.println(a);
    }

    public static int assignID() {
        int a = 10;
        return a;
    }

    public static final String c = "常量";
    public static int b = 6;
    static {
        System.out.println("静态语句块");
    }

    //初始化块2
    {
        System.out.println("初始化块2"); //只有构造类的对象，这些块就会被执行，可以有多个
    }


}

class B extends A {

    public static int assignID() {
        int a = 5;
        return a;
    }
}


