package com.sysu.jianzhi_offer;


public class ListNode {
    private int val;
    private ListNode next = null;


    /**
     * @param a int array
     */
    public static ListNode generate(int[] a) {
        ListNode head = new ListNode(a[0]);
        ListNode curr = head;
        for (int i = 1; i < a.length; i++) {
            ListNode node = new ListNode(a[i]);
            curr.next = node;
            curr = curr.next;
        }
        return head;

    }

    public ListNode(int a ) {
        this.val = a;
        this.next = null;
    }

    public void append(int a) {

        ListNode temp = new ListNode(a);
        if (this.next == null) {
            this.next = temp;
        } else {
            ListNode next = this.next;
            while (next.next != null) {
                next = next.next;
            }
            next.next = temp;
        }


    }

    //求链表的中间结点。如果链表中结点个数为奇数，则返回中间结点；否则返回中间两个结点的任意一个。结点总数不给出，要求只能遍历一次链表。
    public int getTheMediumValue(){
        ListNode first = this;
        ListNode second = this;
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
     * 输入一个链表，输出该链表中倒数第k个结点
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) return head;
        ListNode forwadKth = head;
        ListNode kth = head;

        while (k>0 && forwadKth != null) {
            forwadKth = forwadKth.next;
            k--;
        }
        if (forwadKth == null && k > 0) {
            return null;
        }
        while (forwadKth != null) {
            kth = kth.next;
            forwadKth = forwadKth.next;
        }

        return kth;
    }

    /**
     *输入一个链表，反转链表后，输出新链表的表头。
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null) return head;

        ListNode last = head;
        ListNode curr = head.next;

        last.next = null;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = last;
            last = curr;
            curr = tmp;
        }

        return last;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head;
        if (list1.val > list2.val) {
            head = list2;
            list2 = list2.next;
        } else {
            head = list1;
            list1 = list1.next;
        }
        ListNode curr = head;
        while (list1 != null && list2 != null) {
            while (list2 != null && list1 != null && list1.val >= list2.val) { //等号的情况要考虑
                curr.next = list2;
                curr = curr.next;
                list2 = list2.next;
            }
            while (list1 != null && list2 != null && list1.val < list2.val) {
                curr.next = list1;
                curr = curr.next;
                list1 = list1.next;
            }

        }

        if (list1 == null) curr.next = list2;
        if (list2 == null) curr.next = list1;
        return head;
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        //先求出两个链表的长度差
        int lengthOfList1 = 0;
        int lengthOfList2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != null) {
            lengthOfList1 ++;
            p1 = p1.next;
        }
        while (p2 != null) {
            lengthOfList2 ++;
            p2 = p2.next;
        }

        //然后将较长的链表的指针先走k步
        p1 = pHead1;
        p2 = pHead2;
        int k = lengthOfList1 - lengthOfList2 > 0 ? (lengthOfList1 - lengthOfList2) : (lengthOfList2 - lengthOfList1);
        if (lengthOfList1 > lengthOfList2) {
            while (k > 0 ) {
                p1 = p1.next;
                k--;
            }
        } else {
            while (k > 0 ) {
                p2 = p2.next;
                k--;
            }
        }

        while (p1 != null && p2 != null && p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;


    }

}
