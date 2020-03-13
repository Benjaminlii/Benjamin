package com.Benjamin.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName:Offer38
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉树的深度
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 思路:
 * 递归和非递归两种实现方式
 *
 * @author: Benjamin
 * @date: 19-12-12 下午7:02
 */
public class Offer38 {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 递归实现
     * @param root 根节点
     * @return 树的深度
     */
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
        }
    }

    /**
     * 非递归实现
     * 使用变量存储当前层的节点数,下一层的节点数和当前层遍历到的节点数
     * @param root 根节点
     * @return 树的深度
     */
    public int TreeDepth_(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int thisNum = 1;
        int nextNum = 0;
        int count = 0;
        int ans = 0;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left != null){
                queue.offer(node.left);
                nextNum++;
            }
            if (node.right != null){
                queue.offer(node.right);
                nextNum++;
            }
            count++;
            if (count == thisNum){
                ans++;
                thisNum = nextNum;
                nextNum = 0;
                count = 0;
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
        root.left.right.left = new TreeNode(6);
        root.left.right.left.right = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        System.out.println(new Offer38().TreeDepth_(root));
    }
}
