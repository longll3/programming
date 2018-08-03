package com.sysu;

import com.sysu.Sort;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 14};
        Sort sort = new Sort();
        sort.quickSortWithRecursion(arr, 0, arr.length-1);
        System.out.println(arr);
    }
}
