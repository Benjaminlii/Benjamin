package com.Benjamin.offer;

/**
 * ClassName:Offer62
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉搜索树的第k个结点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 *
 * @author: Benjamin
 * @date: 20-1-7 下午8:40
 */
public class Offer62 {
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

    private int count = 0;

    TreeNode KthNode(TreeNode pRoot, int k) {
        TreeNode ans;
        if (pRoot != null) {
            ans = KthNode(pRoot.left, k);
            if (ans != null) {
                return ans;
            }
            if (k == ++count){
                return pRoot;
            }
            ans = KthNode(pRoot.right, k);
            return ans;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);
        treeNode.right = new TreeNode(7);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(8);
        System.out.println(new Offer62().KthNode(treeNode, 3));
    }
}
