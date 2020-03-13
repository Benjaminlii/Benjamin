package com.Benjamin.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:Offer25
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * 思路:
 * 为了防止重复new节点,需要将遍历过的节点都放入map中,使用原节点去取,如果能取到就说明之前new过了,直接拿来连接即可
 *
 * @author: Benjamin
 * @date: 19-12-1 下午12:57
 */
public class Offer25 {

    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "RandomListNode{" +
                    "label=" + label +
                    ", random=" + this.random.label +
                    ", next=" + next +
                    '}';
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode head = new RandomListNode(pHead.label);
        map.put(pHead, head);

        RandomListNode p = head;

        while (pHead != null) {
            if (pHead.next != null) {
                if (!map.containsKey(pHead.next)) {
                    map.put(pHead.next, new RandomListNode(pHead.next.label));
                }
                p.next = map.get(pHead.next);
            }
            if (pHead.random != null) {
                if (!map.containsKey(pHead.random)) {
                    map.put(pHead.random, new RandomListNode(pHead.random.label));
                }
                p.random = map.get(pHead.random);
            }
            p = p.next;
            pHead = pHead.next;
        }

        return head;
    }

    public static void main(String[] args) {
        RandomListNode randomListNode0 = new RandomListNode(0);
        RandomListNode randomListNode1 = new RandomListNode(1);
        RandomListNode randomListNode2 = new RandomListNode(2);
        RandomListNode randomListNode3 = new RandomListNode(3);
        RandomListNode randomListNode4 = new RandomListNode(4);
        randomListNode0.next = randomListNode1;
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.next = randomListNode4;
        randomListNode0.random = randomListNode3;
        randomListNode1.random = randomListNode4;
        randomListNode2.random = randomListNode1;
        randomListNode3.random = randomListNode3;
        randomListNode4.random = randomListNode0;
        System.out.println(new Offer25().Clone(randomListNode0));
    }
}
