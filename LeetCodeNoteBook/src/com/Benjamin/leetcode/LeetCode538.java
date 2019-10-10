package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode538
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-10 上午9:32
 */
public class LeetCode538 {

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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        System.out.println(new LeetCode538().convertBST(root));
    }

    private int add = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null){
            convertBST(root.right);
            int value = root.val;
            root.val += add;
            add += value;
            convertBST(root.left);
        }
        return root;
    }

}