package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode96
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 不同的二叉搜索树
 * 题目描述
 * 评论 (203)
 * 题解(102)
 * 提交记录
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 示例:
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * ---1         3     3      2      1
 * ----\       /     /      / \      \
 * -----3     2     1      1   3      2
 * ----/     /       \                 \
 * ---2     1         2                 3
 * <p>
 * 思路:
 * 这个题可以理解为n个不同的节点构成的二叉搜索树的种类
 * 没必要去追究数字
 * <p>
 * 二叉树的结构为左子树+根节点+右子树
 * 那么可以把n个节点分为1(根节点) 和 n-1(左右子树节点之和)
 * 如果C(n)表示n个不同的节点构成的二叉搜索树的种类
 * 那么C(n) = C(0)*C(n-1) + C(1)*C(n-2) + C(2)*C(n-3) + ...... + C(n-1)*C(0)
 * 求中C(0)=1, C(1) = 1
 * <p>
 * 关于树,首先想到分治
 * 就是使用递归去解决子树求种类数的问题
 * 时间复杂度非常高
 * <p>
 * 那么考虑动态规划去空间换时间
 * 使用数组存储数据,从0->n去求各个数量的子树构成的二叉搜索树种类
 *
 * @author: Benjamin
 * @date: 20-2-7 下午3:20
 */
public class LeetCode96 {
    /**
     * 分治算法的求解
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += numTrees(i) * numTrees(n - 1 - i);
        }

        return ans;
    }

    /**
     * 动态规划的解法,时间复杂度上面会好很多
     * @param n
     * @return
     */
    public int numTrees_dp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode96().numTrees_dp(3)); // 5
        System.out.println(new LeetCode96().numTrees_dp(5)); // 42
    }
}
