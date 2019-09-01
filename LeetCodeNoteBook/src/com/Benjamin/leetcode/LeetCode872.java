package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个叶值序列 。
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是叶相似的。
 * 如果给定的两个头结点分别为root1和root2的树是叶相似的，则返回true；否则返回false 。
 * <p>
 * 思路:循环遍历连个数的叶子节点,存入list中,然后进行比较即可
 * <p>
 * author:Benjamin
 * date:2019.7.29
 */
public class LeetCode872 {
    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(3);
        treeNode1.left = new TreeNode(5);
        treeNode1.left.left = new TreeNode(6);
        treeNode1.left.right = new TreeNode(2);
        treeNode1.left.right.left = new TreeNode(7);
        treeNode1.left.right.right = new TreeNode(4);
        treeNode1.right = new TreeNode(1);
        treeNode1.right.left = new TreeNode(9);
        treeNode1.right.right = new TreeNode(8);

        new LeetCode872().leafSimilar(treeNode1, new TreeNode(4));

    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();

        getLeaf(root1, leaf1);
        getLeaf(root2, leaf2);

        return leaf1.equals(leaf2);
    }


    public static void getLeaf(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.left == null && treeNode.right == null) {
            list.add(treeNode.val);
        }
        getLeaf(treeNode.left, list);
        getLeaf(treeNode.right, list);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}


