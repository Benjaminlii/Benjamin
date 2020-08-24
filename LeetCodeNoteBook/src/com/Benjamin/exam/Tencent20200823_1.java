package com.Benjamin.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:Tencent20200823_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 20:01
 */
public class Tencent20200823_1 {
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode method(ListNode head, int deleteSub) {
        if (deleteSub == 1) {
            return head.next;
        }
        ListNode preNode = head;
        for (int i = 0; i < deleteSub - 2; i++) {
            preNode = preNode.next;
        }
        preNode.next = preNode.next.next;
        return head;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 0; i < n; i++) {
            node.next = new ListNode(in.nextInt());
            node = node.next;
        }
        head = head.next;
        head = method(head, k);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}