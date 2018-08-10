package com.sysu;

import java.util.LinkedList;
import java.util.Queue;
import com.sysu.Sort;

public class Main {

    public static void main(String[] args) {
        int [] arr = {2,1,4,7,3,6};
        Sort sort = new Sort();
        sort.insertSort(arr);
        for (int element : arr) System.out.print(element+" ");
//        System.out.println(arr);
    }
}
