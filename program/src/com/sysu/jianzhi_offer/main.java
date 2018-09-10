package com.sysu.jianzhi_offer;

import java.util.*;

import com.sun.scenario.animation.AbstractMasterTimer;
import com.sysu.jianzhi_offer.selfStack;

public class main {


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

	/**
	 * @brief 找出所有和为S的连续正数序列
	 */
	public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int border = sum/2;
		int x=1;
		while (x <= border) {
			int n = 2;
			int tmp = x*n + (n*(n-1) / 2);
			while (tmp < sum) {
				n++;
				tmp = x*n + (n*(n-1) / 2);
			}
			if (tmp == sum) {
				ArrayList<Integer> arr = new ArrayList<>();
				for (int i = x; i <= (x+n-1); i++) {
					arr.add(i);
				}
				res.add(arr);
				//x = x+n-1; sum = 19时，不可以
			}
			x++;
		}
		return res;
	}


	/**
	 * @brief LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
	 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
	 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
	 * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
	 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
	 * LL决定去买体育彩票啦。
	 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。
	 * 为了方便起见,你可以认为大小王是0。
	 *
	 * 1. 除0外没有重复的数
	 * 2. max - min < 5
	 */
	public static boolean isContinuous(int [] numbers) {
		if (numbers.length == 0 || numbers == null) return false;

		selectionSort(numbers);

		int min = numbers[numbers.length-1];
		for (int i = 0; i < numbers.length-1; i++) {
			if (numbers[i] == 0) continue;
			if (numbers[i] == numbers[i + 1]) return false;
			if (numbers[i] < min) min = numbers[i];
		}
		if (numbers[numbers.length-1] - min >= 5) return false;

		return true;
	}

	public static void selectionSort(int[] numbers) {
		for (int i = numbers.length-1; i > 1; i--) {
			int max_index = 0;
			int max = 0;
			for (int j = 0; j <= i; j++) {
				if (max < numbers[j]) {
					max = numbers[j];
					max_index = j;
				}
			}
			int tmp = numbers[max_index];
			numbers[max_index] = numbers[i];
			numbers[i] = tmp;
		}
	}

	/**
	 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
	 */
	public int[] multiply(int[] A) {
		int[] b = new int[A.length];
//		int[] first = new int[A.length]; // first[i] = 1*A[0]*A[1]*...A[i-1];
//		int[] second = new int[A.length]; // second[i] = A[n-1]*A[n-2]*...*A[i+1]*1;
		for (int i = 0; i < A.length; i++) {
			if (i == 0) {
				b[i] = 1;
			} else {
				b[i] = A[i-1]*b[i-1];
			}
		}

		int second = 0;
		for (int i = A.length-1; i >= 0; i--) {
			if (i == A.length-1) {
				second = 1;
				b[i] = second*b[i];
			} else {
				second = second * A[i+1];
				b[i] *= second;
			}
		}

		return b;
	}

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
	 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
	 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 */
	//使用二分查找
	public static int minNumberInRotateArray(int [] array) {
		int length = array.length;
		if (length == 0) return 0;
		int left = 0, right = length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (array[mid] > array[right]) {
				left = mid+1;
			} else if (array[mid] < array[right]) {
				right = mid;
			} else {
				right--;
			}

		}
		return array[left];
	}


	/**
	 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
	 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
	 */
	public static int MoreThanHalfNum_Solution(int [] array) {
		int count[] = new int[array.length];
		for (int i = 0 ; i < array.length; i++) {
			int index = i;
			for (int j = 0; j < i; j++) {
				if (array[j] == array[i]) {
					index = j;
					break;
				}
			}
			count[index]++;
		}

		for (int i = 0; i < array.length; i++) {
			if (count[i] > array.length/2) {
				return(array[i]);
			}
		}
		return 0;
	}

	/**
	 * 计算连续子向量的最大和
	 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
	 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
	 */
	public static int FindGreatestSumOfSubArray(int[] array) {
		int dp[] = new int[array.length];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (i == 0) dp[i] = array[i];
			else {
				if (dp[i-1] + array[i] > array[i]) dp[i] = dp[i-1] + array[i];
				else dp[i] = array[i];
			}

			if (max < dp[i]) max = dp[i];
		}

		return max;

	}

	/**
	 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
	 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
	 * @param n
	 * @return
	 * 数学归纳法：找到规律，如个位上的1 的个数为：（n/10)*1 + (if (n % 10 > 1) 1 else ( if (n%10 < 1) 0 else (n%10 - 1 + 1)))
	 *                    如十位上的1 的个数为：（n/100)*10 + (if (n % 100 > 19) 10 else ( if (n%100 < 10) 0 else (n%100 - 10 + 1)))
	 *                    如百位上的1 的个数为：（n/1000)*100 + (if (n % 1000 > 199) 100 else ( if (n%1000 < 100) 0 else (n%10 - 100 + 1)))
	 */
	public static int NumberOf1Between1AndN_Solution(int n) {

		int tmp = 1;
		int count = 0;
		while (tmp <= n) {
			int remainder = n % (tmp*10);
			int quotient = n / (tmp*10);
			count += quotient * tmp + Math.min(tmp, Math.max(0, remainder-tmp+1));
			tmp *= 10;

		}
		return count;
	}

	/**
	 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
	 * @param numbers
	 * @return
	 */
	public static String PrintMinNumber(int [] numbers) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			arr.add(numbers[i]);
		}
		Collections.sort(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = ""+o1 + o2;
				String s2 = ""+o2 + o1;
				return s1.compareTo(s2);
			}
		});

		String res = "";
		for (Integer item : arr) res += item;
		return res;
	}


	/**
	 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 
	 * 如果没有则返回 -1（需要区分大小写）.
	 *
     * 另一种方法，可以使用一个长度为26*2的数组（大小写），模仿map
     *
	 * @param str
	 */
	public static int FirstNotRepeatingChar(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i))) {
				int j = map.get(str.charAt(i));
				map.put(str.charAt(i), j+1);
			} else {
				map.put(str.charAt(i), 1);
			}
		}
		for (int i = 0; i < str.length(); i++) {
			if (map.get(str.charAt(i)) == 1) return i;
		}
        return -1;
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     * @param  array [description]
     * @param  k     [description]
     * @return       [description]
     */
    public int GetNumberOfK(int [] array , int k) {
    	int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
            	while (i < array.length && array[i] == k) {
            		count++;
            		i++;
            	}
            	break;
            }
        }
        return count;
    }

	/**
	 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
	 * @param array
	 * @param num1
	 * @param num2
	 */
	//num1,num2分别为长度为1的数组。传出参数
	//将num1[0],num2[0]设置为返回结果
	public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		//所有值一起异或，相同的数异或为0， 不同的数异或肯定不全为0；
		int tmp = 0;
		for (int num: array) tmp ^= num;

		//找到tmp中为1的那一位，说明不同的那两个数在那一位上一定不同，异或才能为1；
		int index = 0; //标记1的位置,从左往右开始
		while ( (tmp & ( 1 << index)) == 0) index++;

		//根据这一位是否为1将数据分为2部分，每部分都一直异或，留下来的就是其中一个只出现了一次的数了；
		for (int num : array) {
			if ((num & (1<< index)) == 0) {
				num1[0] ^= num;
			} else {
				num2[0] ^= num;
			}

		}
		return;

	}

    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
     * 对应每个测试案例，输出两个数，小的先输出。
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if (array == null || array.length == 0) {
            return res;
        }

        int border = sum / 2;
        //从前往后遍历，遇到>= sum/2的数就可以停止了；同时从后往前，寻找另一个；
        //相差越远，乘积越小
        int front = 0;
        int end = array.length-1;

        while (front <= end && array[front] < border) {
            int biggerNum = sum - array[front];
            while ( array[end] > biggerNum) end--;
            if (array[end] == biggerNum) {
                res.add(array[front]);
                res.add(array[end]);
                break;
            }
            front++;
        }

        return res;

    }

	/**
	 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
	 */
	public String LeftRotateString(String str,int n) {
		int len = str.length();
		if (len == 0) return "";

		str += str;

		return str.substring(n,len+n);
	}


	/**
	 * 反转单词序列
	 * 如：students. a am i
	 * 反转为: i am a students
	 * @param str
	 * @return
	 */
	public static String ReverseSentence(String str) {
		/**
		 * 可以先将整个str都反转一遍，再根据空格确定每个单词的首位，将每个单词都反转一遍
		 */
		if (str == null || str.length() == 0) return "";

		char[] words = str.toCharArray();
		reverse(words, 0, words.length-1);

		int begin = 0, end = 0;
		for (int i = 0; i < words.length; i++) {
		    if (words[i] == ' ') {
		        end = i-1;
		        reverse(words, begin, end);
		        begin = i+1;
            }
        }

        //因为最后一个单词后面没有空格，所以在for循环中不能reverse最后一个单词，要单独reverse
        reverse(words, begin, words.length-1);

		return new String(words);

	}

    /**
     * 将char数组中的char翻转
     * @param chars 要翻转的字符串，翻转范围：【begin， end】
     */
	public static void reverse(char[] chars, int begin, int end) {
	    for (int i = begin, j = end; i < j; i++, j--) {
	        char tmp = chars[i];
	        chars[i] = chars[j];
	        chars[j] = tmp;
        }
        return;
    }

    /**
     * 首先,让小朋友们围成一个大圈。随机指定一个数m,让编号为0的小朋友开始报数。
     * 每次喊到m-1的那个小朋友要出列唱,并且不再回到圈中,
     * 从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友.
     * 哪个小朋友是最后一个小朋友？(注：小朋友的编号是从0到n-1)
     */
    public static int LastRemaining_Solution(int n, int m) {
        int children[] = new int[n];
        int count = 0;

        int index= 0;
        while (count < n-1 ) {
            int num = 0;
            while (true) {
                if (children[index] == 0) {
                    num++;
                    if (num == m) {
                        break;
                    }
                }
                index = (index+1)%n;

            }
            count++;
            children[index] = 1;
            index = (index+1)%n;

        }
        for (int i = 0; i < n; i++) {
            if (children[i] == 0) {
                return i;
            }
        }
        return 0;
    }

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(5);
		TreeNode node5 = new TreeNode(6);
		TreeNode node6 = new TreeNode(7);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		node3.right = node5;
		node2.right = node6;

		int arr[] = {2,4,3,6,3,2,5,5};
		int num1[] = new int[1];
		int num2[] = new int[1];

        System.out.println(LastRemaining_Solution(5, 3));

	}


}
