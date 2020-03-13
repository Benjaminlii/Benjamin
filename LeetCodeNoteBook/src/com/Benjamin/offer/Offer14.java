package com.Benjamin.offer;

/**
 * ClassName:Offer14
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个结点。
 * 思路:
 * 快慢指针
 *
 * @author: Benjamin
 * @date: 19-11-25 下午4:25
 */
public class Offer14 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode p = head;
        try {
            for (int i = 0; i < k; i++) {
                p = p.next;
            }
        }catch (NullPointerException e){
            return null;
        }
        while (p != null) {
            head = head.next;
            p = p.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(6);
        listNode.next = new ListNode(5);
        listNode.next.next = new ListNode(4);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(2);
        listNode.next.next.next.next.next = new ListNode(1);
        System.out.println(new Offer14().FindKthToTail(listNode, 3));
    }
}
