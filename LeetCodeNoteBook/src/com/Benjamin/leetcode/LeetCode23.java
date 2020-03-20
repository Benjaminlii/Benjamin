package com.Benjamin.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName:LeetCode23
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 思路一:
 * 每次找头结点中最小的,链接到结果集后面
 * <p>
 * 思路二:
 * 重复的将后面的链表合并到其中的一个链表中
 * <p>
 * 思路三:
 * 使用分治的思想,两两合并,然后再两两合并,知道剩一个链表为止
 *
 * @author: Benjamin
 * @date: 20-3-20 上午11:32
 */
public class LeetCode23 {
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

    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode ans = new ListNode(0);
        ListNode p = ans;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 == null) {
            p.next = list2;
        }
        if (list2 == null) {
            p.next = list1;
        }
        return ans.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        Queue<ListNode> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < lists.length; i++) {
            ListNode node1 = lists[i++];
            ListNode node2 = i<lists.length?lists[i]:null;
            queue.add(merge(node1,node2));
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

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(4);
        list1.next.next.next = new ListNode(7);
        list1.next.next.next.next = new ListNode(22);

        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(9);
        list2.next.next = new ListNode(21);
        list2.next.next.next = new ListNode(47);
        list2.next.next.next.next = new ListNode(53);

        ListNode list3 = new ListNode(0);
        list3.next = new ListNode(5);
        list3.next.next = new ListNode(13);
        list3.next.next.next = new ListNode(37);
        list3.next.next.next.next = new ListNode(63);

        System.out.println(new LeetCode23().mergeKLists(new ListNode[]{list1, list2, list3}));
        System.out.println(new LeetCode23().mergeKLists(new ListNode[]{}));
        System.out.println(new LeetCode23().mergeKLists(null));

    }

}
