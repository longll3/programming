package com.sysu.jianzhi_offer;

import java.util.*;

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

	/**题目：构建乘积数组
	 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
	 */
	public static int[] multiply(int[] A) {
	    if (A == null || A.length == 0) {
	        return null;
        }

        /**
         * 方法1：利用两个数组分别保存从前至后和从后之前的累加
         * *
        //遍历第一遍，得到数组first，first[i] = A[0]*A[1]*...*A[i];
        int[] first = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (i == 0) first[i] = 1;
            else first[i] = first[i-1]*A[i-1];
        }

        //遍历第二遍，得到数组second，second[i] = A[n-1]*A[n-2]*...*A[n-1];
        int[] second = new int[A.length];
        for (int i = A.length-1; i >= 0; i--) {
            if (i == A.length-1) second[i] = 1;
            else second[i] = second[i+1]*A[i+1];
        }

        //将两个数组中的数相乘
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = first[i]*second[i];
        }
        */

        /**
         * 方法二：只用额外n的空间，从前往后遍历后，再之基础上从后往前累乘后部分 乘积（即second的）
         */
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (i==0) res[i] = 1;
            else res[i] = res[i-1]*A[i-1];
        }

        int second = 0; //保存第二部分
        for (int i = A.length-1; i >= 0; i--) {
            if (i == A.length-1) second = 1;
            else {
                second *= A[i+1];
                res[i] *= second;
            }
        }

        return res;
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

    /**
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */
    public int Add(int num1,int num2) {
        int sum;
        int carry; // 进位
        sum = num1 ^ num2; // 两个数做异或，也就是相加的同时忽略进位
        carry = (num1 & num2) << 1; //得到两个数进位的结果
        while (carry != 0) {
            int sumTmp = sum;
            sum = sum ^ carry;
            carry = (sumTmp & carry) << 1;
        }

        return sum;
    }

    /**
     * 题目："求1+2+3+...+n"
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        //第一种：
        //return (int)(Math.pow(n, 2)+n) >> 1;

        //第二种，利用短路求值
        boolean flag = (n > 0) && (n = Sum_Solution(n-1)+n) != 0;
        return n;
    }

	/**
	 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。
	 * 数值为0或者字符串不是一个合法的数值则返回0。
	 * @param str
	 * @return
	 */
	public static int StrToInt(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		boolean minusSign = false;

		int num = 0;
		for (int i = 0; i < str.length(); i++) {
            char firstBit = str.charAt(i);
		    if (i == 0) {
		        if (firstBit == '-') {
		            minusSign = true;
		            continue;
                } else if (firstBit == '+') {
		            continue;
                } else if (!isNum(firstBit)) {
		            //不是数字
		            return 0;
                }
            }

            if (!isNum(firstBit)) {
                return 0;
            } else {
                num = num * 10 + firstBit-'0';
            }


		}
        if (minusSign) return -num;
		return num;
	}

	public static boolean isNum(char c) {
	    if (c - '0' >= 0 && c -'0' <= 9) {
	        return true;
        }
        return false;
    }


    /**
     * 题目：数组中重复的数字
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
     * 请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        if (length == 0 || numbers == null) {
            return false;
        }

        //先排序
        Arrays.sort(numbers);

        for (int i = 0; i < length-1; i++) {
            if (numbers[i+1] == numbers[i]) {
                duplication[0] = numbers[i];
                return true;
            }
        }

        return false;
    }

	/**题目：数据流中的中位数
	 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
	 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
	 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
	 * @param num
	 */
	public void Insert(Integer num) {
		if ((count & 1) == 0) {
			//插入前是偶数个，则在左边的最大堆插入
			if (max.size() == 0) {
				max.offer(num);
			} else if (num < max.peek()) {
				//如果最大堆不为空，并且num小于max堆顶元素，则将num放进左边的最大堆中
				max.offer(num);
			} else {
				//num大于max堆顶元素，则应将num插入min中，然后再将min中的堆顶元素插入max中
				min.offer(num);
				int tmp = min.poll();
				max.offer(tmp);
			}

		} else {
			//插入前是奇数个，则在右边的最小堆插入
			if (num < max.peek()) {
				max.offer(num);
				int tmp = max.poll();
				min.offer(tmp);
			} else {
				min.offer(num);
			}
		}
		count++;


	}

	int count = 0;
	private PriorityQueue<Integer> min = new PriorityQueue<>(); //中位数右边的最小堆
	private PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	}); //中位数左边的最大堆

	public Double GetMedian() {
		if ((count & 1) == 0) {
			return (double) (max.peek() + min.peek()) / 2;
		} else {
			return (double) max.peek();
		}
	}

	/**题目：正则表达式匹配
	 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
	 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean match(char[] str, char[] pattern)
	{
		if (str.length == 0 && pattern.length == 0) return true;
		if (pattern.length == 0) return false;

		return matchHelp(str, pattern, 0, 0);
	}

	public static boolean matchHelp(char[] str, char[] pattern, int indexStr, int indexPattern) {
		if (indexStr == str.length && indexPattern == pattern.length) return true;
		if (indexStr < str.length && indexPattern == pattern.length) return false;

		//当第二个字符是*时
		if (indexPattern < pattern.length-1 && pattern[indexPattern+1] == '*') {
			//当str的第一个字符匹配pattern的第一个字符
			if (indexStr != str.length && (str[indexStr] == pattern[indexPattern] || pattern[indexPattern] == '.')) {
				return matchHelp(str, pattern, indexStr+1, indexPattern) // str继续向后，可以匹配0～n个
						|| matchHelp(str, pattern, indexStr+1, indexPattern+2) //相当于匹配到最后一个时，都向后移
						|| matchHelp(str, pattern, indexStr, indexPattern+2); // str第一个字符忽略，相当于0个的时候
			} else {
				return matchHelp(str, pattern, indexStr, indexPattern+2);
			}
		} else {
			//当str的字符匹配pattern的字符 或者 pattern是.时（可以匹配任意）
			if (indexStr != str.length && (str[indexStr] == pattern[indexPattern] || pattern[indexPattern] == '.')) {

				return matchHelp(str, pattern, indexStr+1, indexPattern+1);
			} else {
				return false;
			}
		}

	}

	/**题目：表示数值的字符串
	 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
	 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
	 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(char[] str) {
		//数字格式 A[.B][e|E C] .B[e|E C]
		//A 和 C都可有有正负号
		if (str.length == 0) return false;
		int index = 0;
		while (index < str.length) {
			//第一部分A
			if (str[index] == '+' || str[index] == '-') {
				index++;
			}
			int tmp = index;
			while (index < str.length && isNum(str[index])) {
				index++;
			}
			if (index == tmp && str[index] != '.') {
				// +- 号之后没有数字
				return false;
			}

			if (index < str.length) {
				//如果有.
				if (str[index] == '.') {
					index++;
					tmp = index;
					while (index < str.length && isNum(str[index])) {
						index++;
					}
					if (tmp == index) {
						//.后没有数字
						return false;
					}
					if (index == str.length) {
						return true;
					}
					if (str[index] == 'E' || str[index] == 'e') {
						index++;

						if (index < str.length && (str[index] == '+' || str[index] == '-')) {
							index++;
						}
						tmp = index;
						while (index < str.length && isNum(str[index])) {
							index++;
						}
						if (index == tmp) {
							//E|e后没有数字
							return false;
						}
						if (index != str.length) {
							//后面还有非数字的字符
							return false;
						}
					} else {
						return false;
					}

					//如果没有.直接E
				} else if (str[index] == 'e' || str[index] == 'E') {
					index++;
					if (index < str.length && (str[index] == '+' || str[index] == '-')) {
						index++;
					}
					tmp = index;
					while (index < str.length && isNum(str[index])) {
						index++;
					}

					if (index == tmp) {
						//E|e后没有数字
						return false;
					}
					if (index != str.length) {
						//后面还有非数字的字符
						return false;
					}

				} else {
					//再其他字符
					return false;
				}
			} else {
				//一路数字到结束
				return true;
			}

		}
		return true;
	}

	public static boolean isNumeric2(char[] str) {
		boolean doc= false, e=false;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '.') {
				if (doc) return false;
				if (e) return false; // .一定在e前面出现
				doc = true;
			} else if (str[i] == 'E' || str[i] == 'e') {
				if (i == str.length-1) return false; //后面没有数字
				if (e) return false;
				e = true;
			} else if (str[i] == '+' || str[i] == '-') {
				if (e && str[i-1] != 'e' && str[i-1] != 'E') return false; //如有有e,则一定紧跟其后出现
				if (!e && i != 0) return false; //还没有e的话，则只能在第一位出现
			} else if (str[i] < '0' || str[i] > '9') {
				return false;
			}

		}
		return true;
	}


	/**
	 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
	 * @param sequence
	 * @return
	 */
	//BST：二叉树上又加了个搜索的限制。其要求：每个节点比其左子树元素大，比其右子树元素小。
	public static boolean VerifySquenceOfBST(int [] sequence) {
		//最后一个值为根，那么把前面的分为两部分，左边部分比根小，右边部分比根大
		if (sequence.length == 0) return false;
		return judge(sequence, 0, sequence.length-1);
	}

	public static boolean judge(int[] sequence, int start, int end) {
		if (start == end) return true;
		if (end - start == 1 || start - end == 1) return true;

		int root = sequence[end];
		int l = start;
		int r = end-1;
		while (l <= end && sequence[l] < root ) l++;
		while ( r >= start && sequence[r] > root) r--;

		if (l - r != 1) return false;

		return judge(sequence, start, l-1) && judge(sequence, r+1, end-1);
	}


	/**
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
	 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
	 * 数据范围：
	 *
	 * 	对于%50的数据,size<=10^4
	 *
	 * 	对于%75的数据,size<=10^5
	 *
	 * 	对于%100的数据,size<=2*10^5
	 *
	 * @param array
	 * @return
	 */
	public static int InversePairs(int [] array) {
		int [] sorted = new int[array.length];
		for (int i = 0; i< array.length; i++) {
			sorted[i] = array[i];
		}
		int a = digui(array, sorted, 0, array.length-1);
		return a;

	}

	public static int digui(int [] origin, int [] sorted, int start, int end) {
		if (end - start == 0) {
//			b[start] = a[start];
			return 0;
		}

		int position = (end+start) / 2;
		int count = digui(sorted, origin, start, position) + digui(sorted, origin, position+1, end);

		int i = position, j = end;
		int sorted_index = end;
		while(i >= start && j >= position+1) {
			while (origin[i] < origin[j] && i >= start && j >= position+1) {
				sorted[sorted_index--] = origin[j--];
			}
			count += j-position;
			sorted[sorted_index--] = origin[i--];

		}

		while(i >= start) sorted[sorted_index--] = origin[i--];
		while(j >= position+1) sorted[sorted_index--] = origin[j--];

		return count;

	}

	/**
	 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
	 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
	 * 他们的最大值分别为{4,4,6,6,6,5}；
	 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
	 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
	 * @param num
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> maxInWindows(int [] num, int size) {
		ArrayList<Integer> re = new ArrayList<>();

		if (size == 0 || size > num.length) return re;

		int max = 0;
		int max_index = 0;
		for (int i = 0; i < size; i++) {
			if (num[i] > max) {
				max = num[i];
				max_index = i;
			}
		}

		re.add(max);
		for (int i = size; i < num.length; i++) {
			//当前窗口新进了一个更大的值
			if (num[i] > max) {
				max = num[i];
				max_index  = i;
				re.add(max);
			} else {
				//新进来的值不如当前的大，需要看之前的最大值是否已经出去窗口了
				if (i - max_index >= size) {
					//原本的最大值已经出去了，则重新找一个最大值
					max = 0;
					for (int j = 0; j < size; j++) {
						if (num[i-j] > max) {
							max = num[i-j];
							max_index = i-j;
						}
					}
					re.add(max);
				} else {
					//没出去的话，那也就还是原来的最大值
					re.add(max);
				}
			}
		}

		return re;

	}


	public static void main(String[] args) {

//        TreeNode head = new TreeNode(8);
//        TreeNode node1 = new TreeNode(6);
//        TreeNode node2 = new TreeNode(10);
//        TreeNode node3 = new TreeNode(5);
//        TreeNode node4 = new TreeNode(7);
//        TreeNode node5 = new TreeNode(9);
//        TreeNode node6 = new TreeNode(11);
//        head.left = node1;
//        head.right = node2;
//        node1.left = node3;
//        node1.right = node4;
//        node2.left = node5;
//        node2.right = node6;


		System.out.println(isNumeric2("123.45e+6".toCharArray()));

	}


}

