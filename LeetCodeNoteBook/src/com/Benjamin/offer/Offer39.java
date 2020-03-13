package com.Benjamin.offer;

/**
 * ClassName:Offer39
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 平衡二叉树
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * @author: Benjamin
 * @date: 19-12-13 下午7:35
 */
public class Offer39 {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return getHigh(root) != -1;
    }

    private int getHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHigh(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHigh(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }
}
