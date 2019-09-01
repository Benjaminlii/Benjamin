package com.Benjamin.leetcode;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * author:Benjamin
 * date:2019.8.3
 */
public class LeetCode104 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
        System.out.println(new LeetCode104().maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if(root != null){
            return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
        }else{
            return 0;
        }
    }
}

