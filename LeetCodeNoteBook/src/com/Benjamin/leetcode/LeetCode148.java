package com.Benjamin.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName:LeetCode148
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 148. 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author: Benjamin
 * @date: 20-3-18 下午6:10
 */
public class LeetCode148 {
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

    /**
     * 归并排序链表
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        Queue<ListNode> queue = new LinkedList<>();
        int count = 0;

        // 将链表结成单个节点的链表,使用count计数
        while (head != null) {
            ListNode h1 = head;
            ListNode h2 = head.next;
            head = h2 == null ? null : h2.next;
            h1.next = null;
            if (h2 !=null) {
                h2.next = null;
            }
            queue.add(merge(h1,h2));
            count++;
        }
        if (count % 2 != 0) {
            queue.add(null);
            count++;
        }


        while (true) {
            int newCount = 0;
            while (true) {
                queue.add(merge(queue.poll(), queue.poll()));
                newCount++;
                if (newCount == count / 2) {
                    count = newCount;
                    if (count % 2 != 0) {
                        queue.add(null);
                        count++;
                    }
                    break;
                }
            }
            if (newCount == 1) {
                break;
            }
        }
        return queue.poll();
    }

    /**
     * 对两个链表进行合并
     */
    private static ListNode merge(ListNode head1, ListNode head2) {
        ListNode ans = new ListNode(0);
        ListNode node = ans;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                node.next = head1;
                head1 = head1.next;
            } else {
                node.next = head2;
                head2 = head2.next;
            }
            node = node.next;
        }
        if (head1 != null) {
            node.next = head1;
        }
        if (head2 != null) {
            node.next = head2;
        }
        return ans.next;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(9);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(7);
        list.next.next.next.next = new ListNode(3);
        list.next.next.next.next.next = new ListNode(6);
        list.next.next.next.next.next.next = new ListNode(5);
        list.next.next.next.next.next.next.next = new ListNode(4);
        list.next.next.next.next.next.next.next.next = new ListNode(0);
        System.out.println(new LeetCode148().sortList(list));
        System.out.println(new LeetCode148().sortList(null));

    }
}
