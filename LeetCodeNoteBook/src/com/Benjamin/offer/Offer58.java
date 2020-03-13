package com.Benjamin.offer;

/**
 * ClassName:Offer58
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 对称的二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * <p>
 * 思路:
 * 从左右对称的遍历二叉树
 * 每次相当于遍历两棵树,传入下一层的子树相互对称即可
 * 当前层判断同时为null直接返回false,同时不为null则进行下一层的判断
 * 其他情况下返回false
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
        if ((root1 != null && root2 != null) && root1.val == root2.val) {
            return demo(root1.left, root2.right) && demo(root1.right, root2.left);
        } else {
            return root1 == null && root2 == null;
        }
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
