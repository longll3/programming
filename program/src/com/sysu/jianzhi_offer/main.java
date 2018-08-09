package com.sysu.jianzhi_offer;

import com.sysu.sam_DailyQuestion.List;
import com.sysu.jianzhi_offer.UglyNumber;

import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;

public class main {
    public static void main(String[] args) {


        UglyNumber uglyNum = new UglyNumber();
        int ugly = uglyNum.GetUglyNumber_Solution(3);


        System.out.println(ugly);

    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * @param  target [description]
     * @return        [description]
     */
    public int RectCover(int target) {
        int[] dp = new int[target+1];
        for (int i = 1; i <= target; i++) {
        	if (i == 1) {
        		dp[i] = 1;
        	} else if (i == 2) {
        		dp[i] = 2;
        	} else {
        		dp[i] = dp[i-1] + dp[i-2];
        	}
        		
        }
        return dp[target];
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * @param  base     [description]
     * @param  exponent [description]
     * @return          [description]
     */
    public double Power(double base, int exponent) {
     	if (exponent == 0) {
     	  	return 1;
     	}

     	int exp; 
     	double product = base;
     	double res = 1;

     	if (exponent < 0) exp = -exponent;
     	else exp = exponent;

     	while(exp > 0) {
     		if ((exp & 1) == 1) {
     			res *= product;
     		}
     		product *= product;
     		exp = exp >> 1;
     	}

     	return (exponent > 1) ? res : (1/res);
  	}
}
