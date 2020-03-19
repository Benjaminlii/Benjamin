package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import sun.plugin.javascript.navig.LinkArray;

/**
 * ClassName:LeetCode94
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * ...1
 * ....\
 * .....2
 * ..../
 * ...3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 思路:
 * 递归算法:函数返回子树的中序遍历结果,判空后使用addAll添加到结果集
 * 迭代算法:就是非递归中序遍历,使用栈,一直入左子树,没有左子树时从栈中取出一个元素,将其添加到结果集,并一直入其右子树的左子树,
 *
 * @author: Benjamin
 * @date: 20-3-18 下午4:59
 */
public class LeetCode94 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归解法
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ans = new LinkedList<>();

        List<Integer> list = inorderTraversal(root.left);
        if (list != null) {
            ans.addAll(inorderTraversal(root.left));
        }

        ans.add(root.val);

        list = inorderTraversal(root.right);
        if (list != null) {
            ans.addAll(inorderTraversal(root.right));
        }

        return ans;
    }

    /**
     * 迭代解法
     */
    public List<Integer> inorderTraversal_(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root  = root.left;
            }else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                root = node.right;
            }
        }


        return ans;
    }

    /**
     * 莫里斯遍历
     * 空间复杂度为O(1)
     * 如果节点有左子树,那么把整个右子树连接在左子树的最右节点中(这里满足中序遍历的原则)
     * 没有则添加到结果集,向右走
     */
    public List<Integer> inorderTraversal__(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        TreeNode pre;
        while (root!=null){
            if (root.left!=null){
                pre = root.left;
                while (pre.right!=null){
                    pre = pre.right;
                }
                pre.right = root;
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
            }else{
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(new LeetCode94().inorderTraversal(root));
        System.out.println(new LeetCode94().inorderTraversal_(root));
        System.out.println(new LeetCode94().inorderTraversal__(root));
    }

}
