package com.sysu.jianzhi_offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class selfStack {


    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        if (stack.empty()) {
            stack.push(node);
            minStack.push(node);
        } else  {
            stack.push(node);
            if (minStack.peek() < node) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(node);
            }
        }


    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
