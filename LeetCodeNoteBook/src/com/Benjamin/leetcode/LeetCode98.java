package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode98
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 中序遍历该二叉树,记忆上一个遍历到的元素,保持是升序即可
 *
 * @author: Benjamin
 * @date: 19-10-9 下午4:48
 */
public class LeetCode98 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private long min = Long.MIN_VALUE;
    private boolean ans = true;

    public boolean isValidBST(TreeNode root) {
        if (root != null){
            isValidBST(root.left);
            if (root.val > min){
                min = root.val;
            }else{
                ans = false;
            }
            isValidBST(root.right);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Long.MIN_VALUE);
    }
}
