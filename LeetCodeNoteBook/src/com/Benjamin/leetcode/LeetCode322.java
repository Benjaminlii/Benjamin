package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode322
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * @author: Benjamin
 * @date: 20-3-19 下午8:54
 */
public class LeetCode322 {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            // 无法组合使用Integer.MAX_VALUE表示
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin > 0) {
                    // 向前数组越界自然是无法组合
                    // 这里使用等于是让coin==i的情况得到处理
                    int num = i - coin >= 0 ? dp[i - coin] : Integer.MAX_VALUE;
                    min = Math.min(min, num);
                }
            }
            // 控制值不能溢出
            dp[i] = min != Integer.MAX_VALUE ? min + 1 : min;
        }
        // 对结果是无法组合的情况进行转义
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode322().coinChange(new int[]{2}, 3));
        System.out.println(new LeetCode322().coinChange(new int[]{1,2,5}, 11));
        System.out.println(new LeetCode322().coinChange(new int[]{1,3,5}, 0));
        System.out.println(new LeetCode322().coinChange(new int[]{1,2,5,7,9}, 50));
    }

}
