package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName:Offer60
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 把二叉树打印成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 * 思路:
 * 层次遍历
 *
 * @author: Benjamin
 * @date: 20-1-6 下午12:12
 */
public class Offer60 {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int countNext = 0, countCur = 1, countDone = 0;
        Queue<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(pRoot);
        TreeNode node;
        ArrayList<Integer> row = new ArrayList<>();
        while (linkedList.size() != 0) {
            node = linkedList.poll();

            row.add(node.val);
            countDone++;

            if (node.left != null) {
                linkedList.offer(node.left);
                countNext++;
            }
            if (node.right != null) {
                linkedList.offer(node.right);
                countNext++;
            }
            if (countCur == countDone) {
                countDone = 0;
                countCur = countNext;
                countNext = 0;
                ans.add(row);
                row = new ArrayList<>();
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

        System.out.println(new Offer60().Print(node));
    }
}
