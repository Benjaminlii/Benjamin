package com.Benjamin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:LeetCode105
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * ----3
 * ---/ \
 * --9  20
 * ----/  \
 * ---15   7
 * <p>
 * 思路:
 * 这道题在剑指offer已经做过了(Offer4),但是在LeetCode上提交时,发现原有方法的时间复杂度和空间复杂的都非常高
 * 分析有一下原因:
 * 每次都遍历per数组的同时在in数组中找出现的元素,构成了双层for循环,并且搭配递归使用,时间复杂度非常高
 * 每次都复制数组到下一层递归,复制数组非常消耗时间和空间
 * 改进:
 * 使用map存储per中的元素值和下标,减少一层循环
 * 重新封装函数,使用传递边界下标代替复制数组
 *
 * @author: Benjamin
 * @date: 20-2-6 上午10:59
 */
public class LeetCode105 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public static void print_per(TreeNode root) {
            if (root != null) {
                System.out.print(root.val + " ");
                print_per(root.left);
                print_per(root.right);
            }
        }

        public static void print_in(TreeNode root) {
            if (root != null) {
                print_in(root.left);
                System.out.print(root.val + " ");
                print_in(root.right);
            }
        }
    }

    public Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0) {
            return null;
        }

        for (int i = 0; i < preorder.length; i++) {
            map.put(preorder[i], i);
        }

        return buildTree(inorder, 0, inorder.length);
    }

    public TreeNode buildTree(int[] inorder, int startSub, int endSub) {
        if (endSub - startSub == 0) {
            return null;
        }
        int rootSubInInorder = 0;
        int flag = Integer.MAX_VALUE;
        for (int i = startSub; i < endSub; i++) {
            if (flag > map.get(inorder[i])) {
                flag = map.get(inorder[i]);
                rootSubInInorder = i;
            }

        }
        TreeNode root = new TreeNode(inorder[rootSubInInorder]);
//        System.out.println("start = " + startSub + ", end = " + endSub + ", rootSubInInorder = " + rootSubInInorder);
        root.left = buildTree(inorder, startSub, rootSubInInorder);
        root.right = buildTree(inorder, rootSubInInorder + 1, endSub);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new LeetCode105().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

        TreeNode.print_per(root);
        System.out.println();
        TreeNode.print_in(root);
    }

}
