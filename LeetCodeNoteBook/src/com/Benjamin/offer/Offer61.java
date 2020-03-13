package com.Benjamin.offer;

/**
 * ClassName:Offer61
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：
 * 把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）
 * 以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：
 * 根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * 思路:
 * 递归生成序列
 * 同样递归反序列化
 * 使用下标将string转化为输入缓冲区
 *
 * @author: Benjamin
 * @date: 20-1-7 下午8:17
 */
public class Offer61 {

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

    private int index = -1;

    String Serialize(TreeNode root) {
        if (root!=null){
            return root.val + "!" + Serialize(root.left) + "!" + Serialize(root.right);
        }
        return "#";
    }

    TreeNode Deserialize(String str) {
        String[] strs = str.split("!");
        index++;
        TreeNode node = null;
        if (!"#".equals(strs[index])){
            node = new TreeNode(Integer.valueOf(strs[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
//        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);

        System.out.println(treeNode);
        System.out.println(new Offer61().Deserialize(new Offer61().Serialize(treeNode)));
        System.out.println(new Offer61().Serialize(treeNode));
    }

}
