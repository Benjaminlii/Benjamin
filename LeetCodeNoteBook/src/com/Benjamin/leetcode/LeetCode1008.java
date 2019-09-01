package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode1008
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 *
 * @author: Benjamin
 * @date: 19-9-1 上午11:18
 */
public class LeetCode1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode rtn = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode flag = rtn;
            TreeNode newNode = new TreeNode(preorder[i]);
            while (true) {
                if (preorder[i] < flag.val) {
                    if (flag.left == null) {
                        flag.left = newNode;
                        break;
                    } else {
                        flag = flag.left;
                    }
                } else {
                    if (flag.right == null) {
                        flag.right = newNode;
                        break;
                    } else {
                        flag = flag.right;
                    }
                }
            }
        }
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode1008().bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12}));
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
            return "[" + val + " " + left + " " + right + "]";
        }
    }

}
