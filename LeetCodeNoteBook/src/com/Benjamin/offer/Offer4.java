package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer4
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 思路:
 * 使用递归解决
 * 每次在现需队列中寻找第一个出现在当前递归中中序队列的元素,那么这个元素就是这个中序对应的树中的根节点,向下递归,并在最后返回到上一层
 * 向下递归时考虑递归边界,当中序数组长度为0时,数为空,返回null即可.
 *
 * @author: Benjamin
 * @date: 19-11-20 上午11:15
 */
public class Offer4 {
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
                    ", \nleft=" + left +
                    ", \nright=" + right +
                    "\n}";
        }

        public static void print_per(TreeNode root){
            if (root != null){
                System.out.print(root.val + " ");
                print_per(root.left);
                print_per(root.right);
            }
        }
        public static void print_in(TreeNode root){
            if (root != null){
                print_in(root.left);
                System.out.print(root.val + " ");
                print_in(root.right);
            }
        }
        public static void print_hou(TreeNode root){
            if (root != null){
                print_hou(root.left);
                print_hou(root.right);
                System.out.print(root.val + " ");
            }
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if(in.length==0){
            return null;
        }
        // 循环遍历pre,找到第一个能在in中找到的元素
        int rootSub = 0;
        for (int i : pre) {
            boolean flag = false;
            for (rootSub = 0; rootSub < in.length; rootSub++) {
                if (i == in[rootSub]){
                    flag = true;
                    break;
                }
            }
            if (flag){
                break;
            }
        }
        TreeNode root = new TreeNode(in[rootSub]);
        root.left = reConstructBinaryTree(pre, Arrays.copyOfRange(in, 0, rootSub));
        root.right = reConstructBinaryTree(pre, Arrays.copyOfRange(in, rootSub+1, in.length));
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new Offer4().reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        TreeNode.print_per(root);
        System.out.println();
        TreeNode.print_in(root);
        System.out.println();
        TreeNode.print_hou(root);
    }


}
