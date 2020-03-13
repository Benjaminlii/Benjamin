package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:Offer3
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 从尾到头打印链表
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 *
 * 思路一:
 * 递归
 *
 * 思路二:
 * 栈
 *
 * 思路三:
 * LinkedList一直头插
 *
 * @author: Benjamin
 * @date: 19-11-20 上午11:07
 */
public class Offer3 {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode!=null){
            ArrayList<Integer> list = printListFromTailToHead(listNode.next);
            list.add(listNode.val);
            return list;
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        System.out.println(new Offer3().printListFromTailToHead(listNode));
    }
}
