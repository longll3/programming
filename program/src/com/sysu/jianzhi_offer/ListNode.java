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

    /**
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        //首先确定有没有环
        ListNode hasLoop = checkHasLoop(pHead);
        if (hasLoop == null) return null;

        //确定环中节点的个数
        //再次遇到在环中的结点hasLoop就走完了一个环。计数结束
        ListNode tmp = hasLoop.next;
        int count = 1;
        while (tmp != hasLoop) {
            count++;
            tmp = tmp.next;
        }

        //找到环的入口节点
        ListNode firstPtr = pHead;
        ListNode secondPtr = pHead;
        //先走count个结点
        while (count > 0) {
            count--;
            firstPtr = firstPtr.next;
        }
        /**
         * 然后再一起走，相遇时就是环的入口
         *
         * 因为：假设总共n个结点，环中结点有count个，那么第n-count+1个结点就是环的入口结点
         * firstPtr从pHead先走count步，来到第count+1个结点，
         * secondPtr从pHead开始走n-count步，来到第n-count+1个结点，
         * 此时firstPtr总共走了n步，来到n+1个几点，也就是会再次来到环的入口结点，因此他们相遇的时候一定是环的入口结点
         */
        while (firstPtr != secondPtr) {
            firstPtr = firstPtr.next;
            secondPtr = secondPtr.next;
        }

        return firstPtr;

    }

    /**
     * 如果有环，则返回一个在环中的节点，没有则返回null
     * @param head 头节点
     */
    public ListNode checkHasLoop(ListNode head) {
        ListNode slowPtr = head;
        ListNode quickPtr = head;
        while (quickPtr != null) {
            //走一步
            slowPtr = slowPtr.next;

            //走两步
            quickPtr = quickPtr.next;
            if (quickPtr != null) {
                quickPtr = quickPtr.next;
            } else {
                break;
            }

            if (slowPtr == quickPtr) {
                return slowPtr;
            }
        }
        return null;
    }

}
