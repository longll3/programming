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

    /**
     *冒泡排序
     * @描述 从头开始比较，把最大的冒到最后去。
     */

    public void bubbleSort(int[] arr) {
        if (arr.length < 2) return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                   int temp = arr[j];
                   arr[j] = arr[j+1];
                   arr[j+1] = temp;
                }
            }
        }
    }


    /**
     * 堆排序： 参考：http://bubkoo.com/2014/01/14/sort-algorithm/heap-sort/
     * 有3种操作：
     * 最大堆调整（Max-Heapify）：将堆的末端子节点作调整，使得子节点永远小于父节点
     * 创建最大堆（Build-Max-Heap）：将堆所有数据重新排序，使其成为最大堆
     * 堆排序（Heap-Sort）：移除位在第一个数据的根节点，并做最大堆调整的递归运算
     */
    
    /**
     * 从index开始检查并保持堆的最大性质,用于调整
     * @param arr   
     * @param index [检查的起始下标]
     */
    public void maxHeapify(int[] arr, int index, int length) {
        int i = index;
        int leftChild = 2*index+1;
        int rightChild = (i+1)*2;

        if (leftChild < length && arr[i] < arr[leftChild]) {
            //当arr[index] < 左子结点
            i = leftChild;
        }

        if (rightChild < length && arr[i] < arr[rightChild]) {
            i = rightChild;
        }

        //现在arr[i]为index leftChild rightChild 中最大的那个
        if (i != index) {
            //有子结点 > arr[index]
            swap(arr, i, index);
            maxHeapify(arr, i, length);
        }
    }

    //非递归
    public void maxHeapifyNotRecursion(int[] arr, int index, int length) {
        int i = index;
        int leftChild = 2*i+1;
        int rightChild = (i+1)*2;

        while (leftChild < length || rightChild < length) {
            if (leftChild < length && arr[i] < arr[leftChild]) {
                //当arr[index] < 左子结点
                i = leftChild;
            }

            if (rightChild < length && arr[i] < arr[rightChild]) {
                i = rightChild;
            }

            //现在arr[i]为index leftChild rightChild 中最大的那个
            if (i != index) {
                //有子结点 > arr[index]
                swap(arr, i, index);
                index = i;
                leftChild = 2*i + 1;
                rightChild = (i+1)*2;

            } else {
                break;
            }
        }
        
    }
    
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * [根据数组创建堆，自下而上]
     * @param arr 
     */
    public void buildMaxHeap(int[] arr) {
        //从最后一个元素的父结点开始不断调用maxHeapify
        //向下取整
        for (int i = (arr.length-1)/2; i >= 0; i--) {
//            maxHeapify(arr, i, arr.length);
            maxHeapifyNotRecursion(arr, i, arr.length);
        }
    }

    public void heapSort(int[] arr) {
        int length = arr.length;
        buildMaxHeap(arr);
        for (int i = length-1; i > 0; i--) {
            //先将堆顶元素后末尾结点交换
            swap(arr, i, 0);
            //然后再向下调整
//            maxHeapify(arr, 0, i);
            maxHeapifyNotRecursion(arr, 0, i);
        }
    }


    /**
     * 归并排序Merge-Sort
     * 先分解再合并
     * 合并 mergeArray:将两个有序的数组合并起来
     */
    public void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];

        mergeSortHelp(arr, 0, arr.length-1, temp);
    }

    public void mergeSortHelp(int[] arr, int left, int right, int[] tmp){
        if (left < right) {
            int mid = (left+right)/2; //分为2部分
            mergeSortHelp(arr, left, mid, tmp);  // 左边归并排序，使得左子序列有序
            mergeSortHelp(arr, mid+1, right, tmp);  // 右边归并排序，使得右子序列有序
            mergeArray(arr, left, mid, right, tmp);  // 将两个有序子数组合并操作
        }
    }

    public void mergeArray(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left, j = mid+1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
        }
        while (i <= mid) tmp[index++] = arr[i++];
        while (j <= right) tmp[index++] = arr[j++];

        //将tmp的内容拷贝回去
        index = 0;
        while (left <= right) {
            arr[left++] = tmp[index++];
        }
        
    }
    


}
