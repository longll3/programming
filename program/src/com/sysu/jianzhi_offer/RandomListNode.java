package com.sysu.jianzhi_offer;

public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }


    /**
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
     * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     * @param pHead
     * @return
     */
    /**
     * 较优解：
     *
     * 1、复制每个节点，如：复制节点A得到A1，将A1插入节点A后面
     * 2、遍历链表，A1->random = A->random->next;
     * 3、将链表拆分成原链表和复制后的链表
     *
     */
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null) return null;

        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode tmp = head;
        RandomListNode oHead = pHead;

        while (oHead.next != null) {
            RandomListNode newNext = new RandomListNode(oHead.next.label);
            tmp.next = newNext;
            tmp = tmp.next;
            oHead = oHead.next;
        }

        tmp = head;
        oHead = pHead;
        RandomListNode ttmp;
        while (oHead.next != null) {
            ttmp = head;
            if (oHead.random != null) {
                while (ttmp != null) {
                    if (ttmp.label == oHead.random.label) {
                        break;
                    } else {
                        ttmp = ttmp.next;
                    }
                }
                tmp.random = ttmp;
            }

            oHead = oHead.next;
            tmp = tmp.next;

        }

        return head;

    }

}
