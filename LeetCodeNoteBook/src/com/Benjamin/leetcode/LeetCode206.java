package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode206
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 206. 反转链表
 * <p>
 * 思路一:
 * 非递归实现
 * 三指针+循环
 * <p>
 * 思路二:
 * 递归实现
 * 当前层的头结点的next是下一层的尾节点,每层都返回翻转后的头结点
 *
 * @author: Benjamin
 * @date: 20-3-2 上午10:59
 */
public class LeetCode206 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    // 非递归实现
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        // 翻转后链表的头结点
        ListNode node = null;
        // 原链表头结点的后继
        ListNode headNext;
        while (head != null) {
            headNext = head.next;
            head.next = node;
            node = head;
            head = headNext;

        }

        return node;
    }

    // 递归实现
    public ListNode reverseList_(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = reverseList_(head.next);
        head.next.next = head;
        head.next = null;
        return ans;

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
        System.out.println(new LeetCode206().reverseList_(listNode));
    }
}
