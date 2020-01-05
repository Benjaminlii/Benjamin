package com.Benjamin.offer;

/**
 * ClassName:Offer58
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * @author: Benjamin
 * @date: 20-1-5 下午1:51
 */
public class Offer58 {
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

    boolean isSymmetrical(TreeNode pRoot) {
        return demo(pRoot, pRoot);
    }

    boolean demo(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null && root1.val == root2.val) {
            return demo(root1.left, root2.right) && demo(root1.right, root2.left);
        }else if (root1 == null && root2 == null){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(1);
        treeNode.right.left = new TreeNode(1);
        treeNode.right.right = new TreeNode(1);
        System.out.println(new Offer58().isSymmetrical(treeNode));
    }
}
