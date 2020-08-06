package com.Benjamin.leetcode;

import java.util.*;

/**
 * ClassName:Audition0403
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 面试题 04.03. 特定深度节点链表
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 * ........1
 * ......./  \
 * ......2    3
 * ...../ \    \
 * ....4   5    7
 * .../
 * ..8
 * 输出：[[1],[2,3],[4,5,7],[8]]
 * <p>
 * 思路：
 * 使用map的key-value存储 链表内节点层数-链表尾节点
 * 到达新的一层时，给list新增一个节点，存储头节点，并把尾节点存入map（这里是指同一个节点）
 * map的作用就是记录每一层链表的尾节点，方便尾插
 * list主要是组织这些链表，得到最后答案
 *
 * @author: Benjamin
 * @date: 2020-08-06 21:01
 */
public class Audition0403 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
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


    private List<ListNode> list = new ArrayList<>();
    private Map<Integer, ListNode> map = new HashMap();

    public ListNode[] listOfDepth(TreeNode tree) {
        doMethod(tree, 1);
        ListNode[] ans = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public void doMethod(TreeNode tree, int num) {
        if (tree == null) {
            return;
        }
        ListNode node = new ListNode(tree.val);
        if (!map.containsKey(num)) {
            list.add(node);
            map.put(num, node);
        } else {
            ListNode preNode = map.get(num);
            preNode.next = node;
            map.put(num, node);
        }
        doMethod(tree.left, num + 1);
        doMethod(tree.right, num + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);

        System.out.println(Arrays.toString(new Audition0403().listOfDepth(root)));
    }
}
