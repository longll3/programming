package com.sysu.leetcode;

public class Main {
    /**
     * There are N children standing in a line. Each child is assigned a rating value.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     * 扫描2次，第一次从左往右，如果i的分数大于i-1的分数，则i的糖果为i-1的糖果数➕1，否则为1；满足第一个方向
     * 第二次，从右往左，如果i的分数大于i+1的分数，则i的糖果数至少比i+1的多1，满足另一个方向。
     */
    public static int candy(int[] ratings) {
        int count[] = new int[ratings.length];
        count[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                count[i] = count[i-1]+1;
            } else {
                count[i] = 1;
            }
        }

        for (int i = ratings.length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                if (count[i] <= count[i+1]) {
                    count[i] = count[i+1] + 1;
                }
            }
        }

        int sum = 0;
        for (int item : count) {
         sum += item;
        }

        return sum;
    }

    public static void main(String[] args) {
        int arr[] = {5,3,1};
        System.out.println(candy(arr));
    }
}
