package com.Benjamin.leetcode;

import java.util.Stack;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * author:Benjamin
 * date:2019.7.23
 */
public class LeetCode19 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode l = listNode;
        for (int i = 2; i <= 5; i++) {
            l.next = new ListNode(i);
            l = l.next;
        }

        listNode = new LeetCode19().removeNthFromEnd(listNode, 2);

        while (listNode != null) {
            System.out.println(listNode);
            listNode = listNode.next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode rtn = head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode l = null;
        for (int i = 0; i < n; i++) {
            l = stack.pop();
        }
        if (!stack.empty()) {
            head = stack.pop();
            head.next = l.next;
            return rtn;
        } else {
            return l.next;
        }
    }

    private static class ListNode {
        private int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }
}

