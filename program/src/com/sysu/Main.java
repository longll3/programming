package com.sysu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while ((t--) > 0) {
            String s = sc.next();
            if (s.length() > 10) {
                char[] ss = s.toCharArray();
                StringBuffer res = new StringBuffer();
                res.append(ss[0]);
                int count = 0;
                for (int i = 1; i < ss.length; i++) {
                    if (i != ss.length-1) {
                        count++;
                    } else {
                        res.append(count);
                        res.append(ss[i]);
                    }
                }
                System.out.println(res.toString());

            } else {
                System.out.println(s);
            }

        }

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

    private static int assignID() {
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


