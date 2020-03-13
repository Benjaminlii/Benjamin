package com.Benjamin.offer;


import java.util.Stack;

/**
 * ClassName:Offer26
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 思路:
 * 采用非递归遍历的形式,记录前一个节点,与当前遍历到的节点进行连接
 *
 * @author: Benjamin
 * @date: 19-12-1 下午1:23
 */
public class Offer26 {

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
                    ", right=" + right +
                    '}';
        }
    }

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pRootOfTree;
        TreeNode head = pRootOfTree;
        while (head.left != null){
            head = head.left;
        }
        TreeNode p = head;

        while (node != null || !stack.empty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()){
                node = stack.pop();
                if (p != node){
//                    System.out.println(p);
                    p.right = node;
                    node.left = p;
                    p = node;
                }
                node = node.right;
            }
        }
        return head;
    }

    // 递归解法
    private static TreeNode head,node;
    public TreeNode Convert_(TreeNode pRootOfTree) {
        method(pRootOfTree);
        node.right = null;
        return head;
    }
    private static void method(TreeNode root){
        if (root == null){
            return ;
        }
        method(root.left);

        if (node!=null){
            node.right = root;
            root.left = node;
        }else{
            head = root;
            root.left = node;
        }
        node = root;

        method(root.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.left.right.right = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.right.left = new TreeNode(6);
        root1.right.left.right = new TreeNode(7);
        root1.right.right = new TreeNode(9);

        System.out.println(new Offer26().Convert_(root1));
    }
}
