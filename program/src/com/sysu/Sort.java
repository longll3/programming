package com.sysu;

import java.util.Stack;

public class Sort {

    public void quickSortWithRecursion(int[] arr, int left, int right) {
        if (arr.length == 1 || arr.length == 0 || left >= right) {
            return;
        }
        int i = left, j = right;
        int pivot = arr[left];

        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
            }

            while (i < j && arr[i] <= pivot) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
            }
        }
        arr[i] = pivot;

        quickSortWithRecursion(arr, left, i-1);
        quickSortWithRecursion(arr, i+1, right);
    }

    public void quickSortnotRecursion(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        Stack<Integer> stack  = new Stack<>();

        stack.push(0); // start
        stack.push(arr.length-1); // end

        while (!stack.empty()) {
            int j = stack.pop(); //end
            int i = stack.pop(); // start
            int pivotIndex = partition(arr, i, j);
            if (pivotIndex > i) {
                stack.push(i);
                stack.push(pivotIndex-1);
            }
            if (pivotIndex < j) {
                stack.push(pivotIndex+1);
                stack.push(j);
            }
        }
    }

    public int partition(int[] arr, int left, int right) {
        int i = left, j = right;
        int pivot = arr[left];

        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
            }

            while (i < j && arr[i] <= pivot) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
            }
        }
        arr[i] = pivot;
        return i;
    }

    /**
     * 递增插入排序
     * @描述 就是每次把第n个元素插入到前面的n-1个已经排好序的元素中
     */
    public void insertSort(int[] arr) {
        if (arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0; j--) {
                if (temp < arr[j-1]) {
                    arr[j] = arr[j-1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }
    }


}
