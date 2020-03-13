package com.Benjamin.offer;

/**
 * ClassName:Offer15
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 反转链表
 * 输入一个链表，反转链表后，输出新链表的表头。
 * 非递归实现:
 * 三指针,寻常解法
 * 递归实现:
 * 先递归,然后更改之前递归的结果的指针
 * 上一层的head的next还没有被改变,是下层递归返回的倒置链表的尾节点
 *
 * @author: Benjamin
 * @date: 19-11-26 下午12:05
 */
public class Offer15 {
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

    public ListNode ReverseList(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode headNext = null;
        ListNode node = head.next;
        while (head != null) {
            node = head.next;
            head.next = headNext;
            headNext = head;
            head = node;
        }
        return headNext;
    }

    // 递归解法
    public ListNode ReverseList_(ListNode head) {
        if (head == null || head.next==null){
            return head;
        }
        ListNode headNext = ReverseList_(head.next);
        // 这时状态类似  1 -> 2 -> 3 -> 4 -> 5 -> head -> 7 <- 8 <- 9
        // head.next就是下层递归中的尾节点
        // 将尾节点的next链接到这一层递归的头结点,就构成了倒置
        head.next.next = head;
        head.next = null;
        return headNext;
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
        System.out.println(new Offer15().ReverseList_(listNode));
    }
}
