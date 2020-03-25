package com.Benjamin.exam;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:ByteDance20200325
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 实现一个LRU
 * 思路:
 * 使用map+双向链表实现
 *
 * @author: Benjamin
 * @date: 20-3-25 下午4:32
 */
public class ByteDance20200325 {
    private static class LRU {

        private static class ListNode {
            int val;
            ListNode next;
            ListNode pre;

            public ListNode(int val) {
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

        ListNode head;
        ListNode tail;
        int size;
        final int MAXSIZE;
        Map<Integer, ListNode> map = new HashMap<>();

        public LRU(int maxSize) {
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.pre = head;
            size = 0;
            MAXSIZE = maxSize;
        }

        public ListNode get(int val) {
            ListNode node = map.get(val);
            ListNode nodeNext = node.next;
            ListNode nodePre = node.pre;
            nodeNext.pre = nodePre;
            nodePre.next = nodeNext;

            head.next.pre = node;
            node.next = head.next;
            node.pre = head;
            head.next = node;

            return node;
        }

        public void put(int val) {
            if (size == MAXSIZE) {
                System.out.println(111);
                ListNode node = tail.pre;
                map.remove(node.val);
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
                size--;
            }
            ListNode node = new ListNode(val);
            map.put(val, node);
            size++;
            head.next.pre = node;
            node.next = head.next;
            node.pre = head;
            head.next = node;
        }
    }

    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(1);
        lru.put(2);
        lru.put(3);
        System.out.println(lru.head);
        lru.put(4);
        System.out.println(lru.head);
        lru.put(5);
        System.out.println(lru.head);
//        System.out.println(lru.get(2));
    }
}