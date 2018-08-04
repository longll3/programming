package com.sysu.jianzhi_offer;

//把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
public class UglyNumber {
    public int GetUglyNumber_Solution(int index) {

        int[] uglyNumber = new int[index+1];

        int index_2 = 1, index_3 = 1, index_5 = 1;

        for (int i = 1; i <= index; i++) {
            if (i == 1) {
                uglyNumber[i] = 1;
            } else {
                int lastUglyNum = uglyNumber[i-1];

                while (uglyNumber[index_2]*2 <= lastUglyNum) {
                    index_2++;
                }
                while (uglyNumber[index_3]*3 <= lastUglyNum) {
                    index_3++;
                }
                while (uglyNumber[index_5]*5 <= lastUglyNum) {
                    index_5++;
                }

                uglyNumber[i] = Math.min(Math.min(uglyNumber[index_2]*2, uglyNumber[index_3]*3), uglyNumber[index_5]*5);
            }
        }
        return uglyNumber[index];
    }
}
