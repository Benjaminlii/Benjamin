package com.Benjamin.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:Offer55
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 链表中环的入口结点
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * 思路:
 * 使用set存储遍历过的节点,如果当前遍历过的节点之前出现过,那么就是环的入口节点
 *
 * @author: Benjamin
 * @date: 20-1-4 下午2:29
 */
public class Offer55 {
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
                    '}';
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode ans = null;
        Set<ListNode> set = new HashSet<>();
        while (pHead != null){
            if (!set.contains(pHead)){
                set.add(pHead);
                pHead = pHead.next;
            }else{
                ans = pHead;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = listNode.next.next.next;
        System.out.println(new Offer55().EntryNodeOfLoop(listNode));
    }
}
