package com.sysu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Main().new MyThread();
        Thread t2 = new Thread(new Main().new MyRunnable());
        t1.setPriority(3);
        t2.setPriority(8);
        t1.start();
        t2.start();
        t2.join();

        for (int i = 0; i < 100000; i++) {
            i++;
        }
        list.add("main");

        t1.join();

        for (String s: list) {
            System.out.println(s);
        }



    }


    class MyThread extends Thread {
        public void run() {
            for (int i = 0; i < 100000; i++) {
                i++;
            }
            list.add("Thread 1");
        }
    }

    class MyRunnable implements Runnable {
        public void run() {
            for (int i = 0; i < 100000; i++) {
                i++;
            }
            list.add("Thread 2");
        }
    }



    public static int getHeap(int [] count, int q) {
        int i;
        for ( i = 1; i < count.length; i++) {
            if (q <= count[i]) return i;
        }
        return i;
    }

    //最大公约数
    public static int get_gcd(int n1, int n2) {
        int gcd = 0;
        if (n1 < n2) {
            // 交换n1、n2的值
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        if (n1 % n2 == 0) {
            return n2;
        }

        while (n1 % n2 > 0) {
            int tmp = n1 = n1 % n2;
            n1 = n2;
            n2 = tmp;
        }
        return n2;

    }
    //最大公约数，递归法
    public static int get_gcd_with_recuision(int a, int b) {
        int max, min;
        max = (a > b) ? a : b;
        min = (a < b) ? a : b;

        if (max % min != 0) {
            return get_gcd_with_recuision(min, max % min);
        } else
            return min;

    }

    // 最小公倍数
    public static int get_lcm(int n1, int n2) {
        return n1 * n2 / get_gcd(n1, n2);
    }
}

class A {
    public static final String c = "JD";
    static {
        System.out.println("OK");
    }
}

class B extends A {
    static {
        System.out.println("B");
    }
}

