package com.Benjamin.offer;

import java.util.Stack;

/**
 * ClassName:Offer36
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 两个链表的第一个公共结点
 * 输入两个链表，找出它们的第一个公共结点。
 *
 * 有公共节点,说明两条链表是Y字型的,那么两边同时入栈,倒着判断,元素不等时,找到了交界处
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
        Stack<ListNode> sta1 = new Stack<>();
        Stack<ListNode> sta2 = new Stack<>();
        ListNode node = null;
        ListNode ans = null;
        while (pHead1 != null) {
            sta1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            sta2.push(pHead2);
            pHead2 = pHead2.next;
        }
        while (true) {
            if (!sta1.empty() && !sta2.empty() && (node = sta1.pop()) == sta2.pop()) {
                ans = node;
            }else {
                break;
            }
        }
        return ans;
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
