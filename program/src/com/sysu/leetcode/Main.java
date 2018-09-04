package com.sysu.leetcode;

import java.util.ArrayList;

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

    /**
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     *
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     *
     * The minimum path sum from top to bottom is11(i.e., 2 + 3 + 5 + 1 = 11).
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     * @param
     */
    public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int dp[] = new int[triangle.get(triangle.size()-1).size()];
        //从最后一行逆序向上
        for (int i = triangle.size()-2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + (triangle.get(i+1).get(j) > triangle.get(i+1).get(j+1) ? triangle.get(i+1).get(j+1) : triangle.get(i+1).get(j)));
            }

        }

        return triangle.get(0).get(0);

    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * m and n will be at most 100.
     * Input:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * Output: 2
     * Explanation:
     * There is one obstacle in the middle of the 3x3 grid above.
     * There are two ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down -> Down
     * 2. Down -> Down -> Right -> Right
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int dp[][] = new int[m][n];

        if (obstacleGrid[0][0] == 1) return 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else {
                    if (j == 0 && i == 0) {
                        dp[i][j] = 1;
                    } else if (j == 0) {
                        dp[i][j] = dp[i-1][j];
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j-1];
                    } else {
                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    }

                }


            }
        }
        return dp[m-1][n-1];
    }


}
