package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode543 Package:com.Benjamin.leetcode
 * <p>
 * Description: 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 这个问题的答案实际上指的是一颗二叉树中左右子树高度差的最大值
 *
 * @author: Benjamin
 * @date: 20-2-5 下午3:51
 */
public class LeetCode543 {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private int ans = 0;

	public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
	}

	public int depth(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
            int leftDep = depth(root.left);
            int rightDep = depth(root.right);
			ans = Math.max(ans, Math.abs(leftDep + rightDep));
            return Math.max(leftDep, rightDep) + 1;
		}
	}

    public static void main(String[] args) {
	    TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        System.out.println(new LeetCode543().diameterOfBinaryTree(root));
    }
}
