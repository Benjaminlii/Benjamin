package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode113
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * @author: Benjamin
 * @date: 19-8-9 上午9:24
 */
public class LeetCode113 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(new LeetCode113().pathSum(root, 22));

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> rtn = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        path(root, sum, list, rtn);
        return rtn;
    }

    /**
     * @param list 保存当前路径,向下递归一层时将当前结点放入,退出当前函数时讲当前结点取出
     * @param rtn 总结果
     */
    public void path(TreeNode root, int sum, List<Integer> list, List<List<Integer>> rtn){
        if (root == null){
            return ;
        }

        list.add(root.val);

        //sum减当前结点的值判断是否为零
        if(root.left == null && root.right == null && sum - root.val == 0) {
            rtn.add(new ArrayList<>(list));
        }

        path(root.left, sum-root.val, list, rtn);
        path(root.right, sum-root.val, list, rtn);

        list.remove(list.size()-1);
    }

}
