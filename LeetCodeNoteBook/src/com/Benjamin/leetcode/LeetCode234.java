package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode234
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * <p>
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 思路：
 * 找到链表的中间节点，然后反转后半部分，进行比较后反转回去。
 * 这样做的好处是不需要额外的空间，并且时间复杂度为O(n)
 * 中间节点可以使用二倍速的快慢指针得到
 *
 * @author: Benjamin
 * @date: 2020-10-23 14:49
 */
public class LeetCode234 {
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

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 快慢指针找到后一半链表
        ListNode endOfFirstList = getEndOfFirstList(head);
        // 进行反转
        ListNode secondList = reverseList(endOfFirstList.next);

        // 根据后一半指针进行对比
        boolean ans = true;
        ListNode firstList_ = head;
        ListNode secondList_ = secondList;
        while (secondList_ != null) {
            if (firstList_.val != secondList_.val) {
                ans = false;
                break;
            }

            firstList_ = firstList_.next;
            secondList_ = secondList_.next;
        }

        endOfFirstList.next = reverseList(secondList);

        return ans;
    }

    /**
     * 通过快慢指针找到前链表的尾节点
     * 之所以要找前半链表的尾节点是因为这样既可以找到后半链表，也可以方便后面再次进行反转恢复链表
     */
    private ListNode getEndOfFirstList(ListNode listNode) {
        ListNode fast = listNode;
        ListNode slow = listNode;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反转链表，并返回反转后链表的头节点
     */
    private ListNode reverseList(ListNode listNode) {
        // 后插法
        ListNode ans = new ListNode(0);
        ListNode node = null;
        while (listNode != null) {
            ListNode mark = listNode.next;
            listNode.next = node;
            ans.next = listNode;
            node = ans.next;

            listNode = mark;
        }
        return ans.next;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(3);
        list.next.next.next.next.next = new ListNode(2);
        list.next.next.next.next.next.next = new ListNode(1);

        System.out.println(new LeetCode234().isPalindrome(list));
        System.out.println(new LeetCode234().isPalindrome(list.next));
    }
}
