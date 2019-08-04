package com.Benjamin.leetcode;

public class LeetCode222 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);

        System.out.println(new LeetCode222().countNodes(root));
    }

    public int countNodes(TreeNode root) {
        if(root != null){
            return countNodes(root.left) + countNodes(root.right) + 1;
        }else{
            return 0;
        }
    }

}
