package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName:Offer22
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 从上往下打印二叉树
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * <p>
 * 使用队列实现
 * 注意队列中插入null值是允许的,所以要自行进行判断,null不进行插入
 *
 * @author: Benjamin
 * @date: 19-11-29 上午10:11
 */
public class Offer22 {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while ((root = queue.poll()) != null) {
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            ans.add(root.val);
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
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(2);
        root2.left.left.left.left = new TreeNode(1);
        System.out.println(new Offer22().PrintFromTopToBottom(root2));
    }
}
