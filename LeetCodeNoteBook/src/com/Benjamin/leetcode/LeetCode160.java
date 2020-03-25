package com.Benjamin.leetcode;

import java.util.Stack;

/**
 * ClassName:LeetCode160
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 思路:
 * 之前剑指offer写过,Y字形的链表
 *
 * @author: Benjamin
 * @date: 20-3-25 下午2:29
 */
public class LeetCode160 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();

        while (headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }
        ListNode ans = null;
        while (stackA.peek() == stackB.peek()) {
            ans = stackA.pop();
            stackB.pop();
        }

        return ans;
    }

    public static void main(String[] args) {
        ListNode ans = new ListNode(100);
        ans.next = new ListNode(101);
        ans.next.next = new ListNode(102);
        ans.next.next.next = new ListNode(103);

        ListNode headA = new ListNode(1);
        headA.next = new ListNode(5);
        headA.next.next = new ListNode(8);
        headA.next.next.next = ans;

        ListNode headB = new ListNode(98);
        headB.next = new ListNode(952);
        headB.next.next = ans;

        System.out.println(new LeetCode160().getIntersectionNode(headA, headB));
    }

}
