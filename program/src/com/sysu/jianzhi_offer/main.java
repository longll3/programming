package com.sysu.jianzhi_offer;

import java.util.*;

import com.sysu.jianzhi_offer.selfStack;

public class main {
    public static void main(String[] args) {


		Queue<TreeNode> q = new LinkedList<>();
		TreeNode a = null;
		TreeNode b = new TreeNode(1);
		TreeNode c = new TreeNode(2);
		TreeNode d = new TreeNode(3);

		q.add(b);
		q.add(c);
		q.add(a);
		q.add(d);

		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());

		int l = q.size();


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

	/**
	 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
	 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
	 * @param array
	 */
	public void reOrderArray(int [] array) {
        // 使用插入排序
        for (int i = 1; i < array.length; i++) {
            if (array[i]%2 == 1) {
                int temp = array[i];
                int j;
                for (j = i; j > 0; j--) {
                    if (array[j-1] % 2 == 0) {
                        array[j] = array[j-1];
                    } else {
                        break;
                    }
                }
                array[j] = temp;
            }
            
        } 
        
	}


	/**
	 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
	 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
	 * @输入描述 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
	 */
	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str.length() == 0 || str == null) {
			return res;
		}
		Set<String> result = new HashSet<>();
		char[] chars = str.toCharArray();
		permutation(chars, result, 0);
		res.addAll(result);
		Collections.sort(res);
		return res;

	}

	public static void permutation(char[] str, Set<String> result, int begin) {
		//到了最后一个字符时，就不用在分了
		if (str.length - begin <= 1 ) {
			result.add(new String(str));
			return;
		}

		for (int i = begin; i < str.length; i++) {
			swap(str, begin, i);
			permutation(str, result, begin+1);
			swap(str,i, begin); // 要复位，不然上一步的交换会使原始字符串发生变化，那么在分割的时候就会漏掉很多情况
		}

		return;

	}

	public static void swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}

	/**
	 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
	 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
	 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
	 * 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
	 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
	 */
	public static boolean hasPath(char[] matrix, int rows, int cols, char[] str)
	{
		boolean[] isVisited = new boolean[matrix.length];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				//分别用矩阵中的每个位置都作为起点
				if (hasPathHelper(matrix, rows, cols, i, j, str, 0, isVisited)) {
					return true;
				}

			}
		}
		return false;
	}

	public static boolean hasPathHelper(char[] matrix, int rows, int cols, int startaRow, int startCol, char[] str, int index, boolean[] isVisited) {
		if (index == str.length) {
			return true;
		}
		if (startaRow < 0 || startaRow >= rows || startCol < 0 || startCol >= cols) {
			//已全部找完都没有找到
			return false;
		}

		int crrPos = startaRow*cols+startCol;
		boolean result = false;
		if (matrix[crrPos] == str[index] && !isVisited[crrPos]) {
			isVisited[crrPos] = true;
			result = hasPathHelper(matrix, rows, cols, startaRow-1, startCol, str, index+1, isVisited) ||
					 hasPathHelper(matrix, rows, cols, startaRow, startCol+1, str, index+1, isVisited) ||
					 hasPathHelper(matrix, rows, cols, startaRow, startCol-1, str, index+1, isVisited) ||
					 hasPathHelper(matrix, rows, cols, startaRow+1, startCol, str, index+1, isVisited);
			isVisited[crrPos] = false; //这一步就是用来回溯的，恢复到没走过的状态。
		}
		return result;
	}

	/**
	 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
	 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
	 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
	 */
	public static ArrayList<Integer> printMatrix(int [][] matrix) {
		ArrayList<Integer> res = new ArrayList<>();
		int i = 0, j = 0;
		int rows = matrix.length;
		int cols = matrix[0].length;

		int size = rows*cols;

		if (rows == 0 || cols == 0) return res;

		int count = 0; //遍历过的数目

		int left_up = 0, left_down = -1, right_up = cols, right_down = rows;
		while (count < size) {
			//从左往右
			for (; j < right_up && count < size; j++) {
				count ++;
				res.add(matrix[i][j]);
			}
			right_up--;
			j--;
			i++;

			//从上往下
			for (; i < right_down && count < size; i++) {
				count ++;
				res.add(matrix[i][j]);
			}
			right_down--;
			i--;
			j--;


			//从右往左
			for (; j > left_down && count < size; j--) {
				count ++;
				res.add(matrix[i][j]);
			}
			left_down++;
			j++;
			i--;

			//从下往上
			for (; i > left_up && count < size; i--) {
				count ++;
				res.add(matrix[i][j]);
			}
			left_up++;
			i++;
			j++;



		}

		return res;
	}


	/**
	 * @breif 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
	 * 假设压入栈的所有数字均不相等。
	 * 例如序列1,2,3,4,5是某栈的压入顺序，
	 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
	 * （注意：这两个序列的长度是相等的）
	 */
	public boolean IsPopOrder(int [] pushA,int [] popA) {
		if (popA.length == 0) return true;

		Stack<Integer> s = new Stack<>();
		for (int i = 0, j=0; i < pushA.length;i++) {
			s.push(pushA[i]);
			while (j < popA.length && s.peek() == popA[j]) {
				s.pop();
				j++;
			}
		}

		if (s.empty()) return true;
		else return false;

	}


}
