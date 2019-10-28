package com.Benjamin.leetcode;


import java.util.*;

/**
 * ClassName:LeetCode652
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * 示例 1：
 *        1
 *       / \
 *      2   3
 *     /   / \
 *    4   2   4
 *   /
 *  4
 * 下面是两个重复的子树：
 *      2
 *     /
 *    4
 * 和
 *      4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 *
 * @author: Benjamin
 * @date: 19-10-28 下午5:18
 */
public class LeetCode652 {
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

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> rtn = new ArrayList<>();

        if (root == null) {
            return rtn;
        }

        // 遍历这颗树,顺序无所谓
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();

            String ans = inOrderTraversal(node);
            if (map.get(ans) != null && map.get(ans) == 1) {
                rtn.add(node);
            } else {
                map.put(ans, 1);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return rtn;
    }

    public static String inOrderTraversal(TreeNode root) {
        if (root != null) {
            return inOrderTraversal(root.left) + "," + root.val + "," + inOrderTraversal(root.right);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);
        System.out.println(new LeetCode652().findDuplicateSubtrees(root));
    }
}
