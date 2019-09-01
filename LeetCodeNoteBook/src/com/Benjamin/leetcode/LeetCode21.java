package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode21
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author: Benjamin
 * @date: 19-8-11 上午10:01
 */
public class LeetCode21 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(4);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);

        System.out.println(new LeetCode21().mergeTwoLists(listNode1, listNode2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode flag1 = l1, flag2 = l2;
        ListNode rtn = new ListNode(0);
        ListNode flag = rtn;

        while (flag1 != null && flag2 != null) {
            int val;
            if (flag1.val > flag2.val) {
                val = flag2.val;
                flag2 = flag2.next;
            } else {
                val = flag1.val;
                flag1 = flag1.next;
            }
            flag.next = new ListNode(val);
            flag = flag.next;
        }

        while (flag1 != null) {
            flag.next = new ListNode(flag1.val);
            flag1 = flag1.next;
            flag = flag.next;
        }

        while (flag2 != null) {
            flag.next = new ListNode(flag2.val);
            flag2 = flag2.next;
            flag = flag.next;
        }

        return rtn.next;
    }

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

}
