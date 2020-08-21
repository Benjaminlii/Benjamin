package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode337
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 * .....3
 * ..../ \
 * ...2   3
 * ....\   \
 * .....3   1
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * <p>
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 * .....3
 * ..../ \
 * ...4   5
 * ../ \   \
 * .1   3   1
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * <p>
 * 思路一：
 * 简单点，就是每一个节点带一个标记，如果标记为true，可以偷当前节点（但不一定偷），如果偷，那么下一层的都不可以偷了
 * 如果为false，那么当前节点不偷，下一级可以偷
 * <p>
 * 思路二：
 * 上面的思路中，会出现重复的递归，（一个节点会true，false两次遍历）时间复杂度有些高
 * 经过优化，可以返回一个数组，下标0代表不偷当前节点，下标1代表偷
 *
 * @author: Benjamin
 * @date: 2020-08-20 21:39
 */
public class LeetCode337 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 思路一
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        return Math.max(rob(root, true), rob(root, false));
    }

    private int rob(TreeNode root, boolean flag) {
        if (root == null) {
            return 0;
        }
        // 当前节点允许偷
        // 那么当前节点可以偷也可不偷
        // 那么下一层不行了
        int ans1 = 0;
        if (flag) {
            // 偷当前节点
            int num1 = rob(root.left, false);
            int num2 = rob(root.right, false);
            ans1 = root.val + num1 + num2;

        }
        // 当前节点不偷
        int num1 = rob(root.left, true);
        int num2 = rob(root.right, true);
        int ans2 = num1 + num2;
        return Math.max(ans1, ans2);
    }

    /**
     * 思路二
     *
     * @param root
     * @return
     */
    public int rob_(TreeNode root) {
        int[] ans = rob__(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] rob__(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] ans = new int[2];
        int[] leftAns = rob__(root.left);
        int[] rightAns = rob__(root.right);

        ans[0] = Math.max(leftAns[0], leftAns[1]) + Math.max(rightAns[0], rightAns[1]);
        ans[1] = leftAns[0] + rightAns[0] + root.val;
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.left.right = new TreeNode(3);
        root1.right = new TreeNode(3);
        root1.right.right = new TreeNode(1);
        System.out.println(new LeetCode337().rob_(root1));

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right = new TreeNode(5);
        root2.right.right = new TreeNode(1);
        System.out.println(new LeetCode337().rob_(root2));
    }
}
