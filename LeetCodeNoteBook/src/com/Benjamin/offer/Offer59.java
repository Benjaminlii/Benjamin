package com.Benjamin.offer;

import java.util.*;

/**
 * ClassName:Offer59
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 按之字形顺序打印二叉树
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印
 * 其他行以此类推。
 *
 * 思路:
 * 层次遍历二叉树,使用一个状态变量存储当前是否是从左到右打印,初始为true
 * 还用计数器记录当前行的节点个数,另一个计数器记录已经遍历过的当前行节点的个数
 * 使用链表存储节点,从左到右输出时,取最左侧元素,插入到最右侧
 * 从右到左反之
 *
 * @author: Benjamin
 * @date: 20-1-6 上午11:16
 */
public class Offer59 {
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

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null){
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int countNext = 0, countCur = 1, countDone = 0;
        boolean isLeftToRight = true;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(pRoot);
        TreeNode node;
        ArrayList<Integer> row = new ArrayList<>();
        while (linkedList.size()!=0){
            if (!isLeftToRight) {
                node = linkedList.remove(linkedList.size()-1);
            }else {
                node = linkedList.remove(0);
            }
            row.add(node.val);
            countDone++;
            if (isLeftToRight){
                if (node.left != null) {
                    linkedList.add(node.left);
                    countNext++;
                }
                if (node.right != null) {
                    linkedList.add(node.right);
                    countNext++;
                }
            }else{
                if (node.right != null) {
                    linkedList.add(0, node.right);
                    countNext++;
                }
                if (node.left != null) {
                    linkedList.add(0, node.left);
                    countNext++;
                }
            }
            if (countCur == countDone){
                countDone = 0;
                countCur = countNext;
                countNext = 0;
                ans.add(row);
                row = new ArrayList<>();
                isLeftToRight = !isLeftToRight;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.left = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);
        System.out.println(new Offer59().Print(node));
    }
}
