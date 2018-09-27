package com.sysu.jianzhi_offer;
import java.util.Stack;

//用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
public class queue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {

        stack1.push(node);


    }

    public int pop() {
        if (stack2.empty()) {
            return pop(stack1, stack2);
        } else {
            return stack2.pop();
        }
    }

    public int pop(Stack<Integer> from, Stack<Integer> to) {
        while (!from.empty()) {
            to.push(from.pop());
        }
        return to.pop();
    }


}





