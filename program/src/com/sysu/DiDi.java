package com.sysu;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.net.Inet4Address;
import java.util.*;

public class DiDi {
    public static void main(String[] args) {
        ArrayList<Integer> depth = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> map = new TreeMap<>();
        int s = 4;
        while (s > 0) {
            int i, j;
            i = sc.nextInt();
            j = sc.nextInt();
            map.put(i,j);
            s--;

        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int i = entry.getKey();
            int j = entry.getValue();
            if (j >= depth.size()) {
                int diff = j - depth.size() + 1;
                while (diff-- > 0) {
                    depth.add(1);
                }
            }
            depth.set(j,depth.get(i) + 1);
        }

        int max = 0;
        for (int item : depth) {
            if(item > max) max = item;
        }
        System.out.println(max);






    }

    public static void merge(int[] arr) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        int left = 0, right = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) continue;
            if (arr[i] < min) {
                min = arr[i];
                index = i;
                if (i > 0) left = i-1;
                if (i < arr.length-1) right = i + 1;
            }
        }

    }

    public static void merge(ArrayList<Integer> arr) {
        int min1 = Integer.MAX_VALUE;
        int index1 = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < min1) {
                min1 = arr.get(i);
                index1 = i;

            }
        }

        if (index1 == 0) {
            arr.set(index1+1, arr.get(index1) + arr.get(index1+1));
        } else if (index1 == arr.size()-1) {
            arr.set(index1-1, arr.get(index1) + arr.get(index1-1));

        } else {
            if (arr.get(index1 -1) < arr.get(index1+1)) {
                arr.set(index1-1, arr.get(index1) + arr.get(index1-1));
            } else {
                arr.set(index1+1, arr.get(index1) + arr.get(index1+1));
            }
        }
        arr.remove(index1);
    }
}
