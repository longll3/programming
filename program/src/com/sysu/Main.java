package com.sysu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) { // 这一句很重要！！！
            int n = sc.nextInt();
            int[] arr = new int[n];


            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    arr[i] = arr[i-1] + sc.nextInt();
                } else {
                    arr[i] = sc.nextInt();
                }


            }
            int m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                int q = sc.nextInt();

                System.out.println(binary_find(arr, n, q) + 1);


            }
        }

    }

    public static int binary_find(int arr[], int length, int num) {
        if (arr.length <= 0) {
            return -1;
        }
        int i = 0;
        int j = length - 1;
        int mid;
        while (i <= j) {
            mid = (i + j) / 2;
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                if (mid > i && arr[mid - 1] < num || mid == i) {
                    return mid;
                }
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return -1;
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
