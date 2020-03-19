package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode203
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 203. 移除链表元素
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * 思路:
 * 使用双指针会比较好捋清关系,用node节点表示后面的指针,相同head跟上node,不同则head的next进行连接
 *
 * @author: Benjamin
 * @date: 20-3-19 下午10:52
 */
public class LeetCode203 {
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

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode ans = new ListNode(0);
        ans.next = head;
        head = ans;
        ListNode node = head.next;
        while (node != null) {
            if (node.val == val) {
                head.next = node.next;
            } else {
                head = node;
            }
            node = node.next;
        }
        return ans.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next = new ListNode(6);
        System.out.println(new LeetCode203().removeElements(head, 6));
        System.out.println(new LeetCode203().removeElements(head, 6));
    }
}
