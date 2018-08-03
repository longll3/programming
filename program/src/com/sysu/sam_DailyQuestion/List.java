package com.sysu.sam_DailyQuestion;

public class List {
    private int val;
    private List next;


    //求链表的中间结点。如果链表中结点个数为奇数，则返回中间结点；否则返回中间两个结点的任意一个。结点总数不给出，要求只能遍历一次链表。
    public int getTheMediumValue(){
        List first = this;
        List second = this;
        while(first != null) {
            first = first.next;
            if (first != null) {
                first = first.next;
            }
            second = second.next;
        }
        return second.val;
    }

    /**
     * @param a int array
     */
    public List(int[] a) {

        for (int i = 0; i < a.length; i++) {
            this.append(a[i]);
        }

    }

    public List(int a ) {
        this.val = a;
        this.next = null;
    }

    public void append(int a) {

        List temp = new List(a);
        if (this.next == null) {
            this.next = temp;
        } else {
            List next = this.next;
            while (next.next != null) {
                next = next.next;
            }
            next.next = temp;
        }


    }

}
