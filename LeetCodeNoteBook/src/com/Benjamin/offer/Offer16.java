package com.Benjamin.offer;

/**
 * ClassName:Offer16
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 合并两个排序的链表
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * @author: Benjamin
 * @date: 19-11-26 下午12:29
 */
public class Offer16 {

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

    public ListNode Merge(ListNode list1, ListNode list2) {
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

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(5);
        listNode1.next.next.next = new ListNode(7);
        listNode1.next.next.next.next = new ListNode(9);
        ListNode listNode2 = new ListNode(2);
        listNode2.next = new ListNode(4);
        listNode2.next.next = new ListNode(6);
        listNode2.next.next.next = new ListNode(8);
        listNode2.next.next.next.next = new ListNode(10);

        System.out.println(new Offer16().Merge(listNode1, listNode2));
    }
}
