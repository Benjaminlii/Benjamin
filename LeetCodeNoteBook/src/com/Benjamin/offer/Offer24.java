package com.Benjamin.offer;

import java.util.ArrayList;

/**
 * ClassName:Offer24
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 * <p>
 * 使用递归遍历树,进入递归时在路径列表中加入本节点,退出是删除
 * 每次传入下一层的是下一层需要进行的步数
 * 当下一层没有子节点并且没有剩余步数时复制列表到结果集
 *
 * @author: Benjamin
 * @date: 19-11-30 下午12:41
 */
public class Offer24 {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    public ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public ArrayList<Integer> list = new ArrayList<>();


    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return ans;
        }
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            ans.add(new ArrayList<>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size()-1);
        return ans;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(10);
        t1.right = new TreeNode(5);
        t1.left = new TreeNode(12);
        t1.right.left = new TreeNode(4);
        t1.right.right = new TreeNode(7);
        t1.right.left.left = new TreeNode(3);
        System.out.println(new Offer24().FindPath(t1, 22));
    }
}
