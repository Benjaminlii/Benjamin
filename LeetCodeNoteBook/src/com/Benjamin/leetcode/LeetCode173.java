package com.Benjamin.leetcode;

import java.util.Stack;

/**
 * ClassName:LeetCode173
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * 思路:
 * 使用栈
 *
 * @author: Benjamin
 * @date: 19-10-17 上午11:16
 */
public class LeetCode173 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            while (root!= null){
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode ans = stack.pop();
            TreeNode node = ans.right;
            while (node!= null){
                stack.push(node);
                node = node.left;
            }
            return ans.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.empty();
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());// 返回 3
        System.out.println(iterator.next());// 返回 7
        System.out.println(iterator.hasNext());//返回 true
        System.out.println(iterator.next());// 返回 9
        System.out.println(iterator.hasNext());//返回 true
        System.out.println(iterator.next());// 返回 15
        System.out.println(iterator.hasNext());//返回 true
        System.out.println(iterator.next());// 返回 20
        System.out.println(iterator.hasNext());//返回 false
    }
}
