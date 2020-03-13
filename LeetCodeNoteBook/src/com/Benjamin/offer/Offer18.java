package com.Benjamin.offer;

/**
 * ClassName:Offer18
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉树的镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * @author: Benjamin
 * @date: 19-11-27 下午12:31
 */
public class Offer18 {

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
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void Mirror(TreeNode root) {
        if (root == null){
            return;
        }
        Mirror(root.left);
        Mirror(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        System.out.println(treeNode);
        new Offer18().Mirror(treeNode);
        System.out.println(treeNode);
    }
}
