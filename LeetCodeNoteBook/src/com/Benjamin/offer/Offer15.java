package com.Benjamin.offer;

/**
 * ClassName:Offer15
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 输入一个链表，反转链表后，输出新链表的表头。
 *
 * @author: Benjamin
 * @date: 19-11-26 下午12:05
 */
public class Offer15 {
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

    public ListNode ReverseList(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode headNext = null;
        ListNode node = head.next;
        while (head != null) {
            node = head.next;
            head.next = headNext;
            headNext = head;
            head = node;
        }
        return headNext;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);

        System.out.println(listNode);
        System.out.println(new Offer15().ReverseList(listNode));
    }
}
