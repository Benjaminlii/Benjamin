package com.Benjamin.offer;

import java.util.Stack;

/**
 * ClassName:Offer36
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 输入两个链表，找出它们的第一个公共结点。
 *
 * @author: Benjamin
 * @date: 19-12-8 下午3:41
 */
public class Offer36 {

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

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> node1 = new Stack<>();
        Stack<ListNode> node2 = new Stack<>();
        ListNode node = null;
        ListNode node_ = null;
        while (pHead1 != null) {
            node1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            node2.push(pHead2);
            pHead2 = pHead2.next;
        }
        while (true) {
            if (!node1.empty() && !node2.empty() && (node = node1.pop()) == node2.pop()) {
                node_ = node;
            }else {
                break;
            }
        }
        return node_;
    }

    public static void main(String[] args) {
        ListNode tail = new ListNode(0);
        tail.next = new ListNode(0);
        tail.next.next = new ListNode(0);

        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(2);
        h1.next.next = new ListNode(3);
        h1.next.next.next = tail;

        ListNode h2 = new ListNode(6);
        h2.next = new ListNode(7);
        h2.next.next = new ListNode(8);
        h2.next.next.next = tail;

        System.out.println(new Offer36().FindFirstCommonNode(tail, tail));
    }
}
