package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode309
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 思路一：
 * 动态规划
 * 使用dp[i]存储从0~i天进行若干次买卖（含冷冻期），所能获得的最大利润
 * i向后移动一位，只需要遍历前面的序列，在满足冷冻期的情况下选择出又进行了一次买卖的最大利润即可
 * 思路二：
 * dp[i][0]表示第i天之后（即第i+1天，下同）手中有股票时的最大收益
 * dp[i][1]表示第i天之后手中无股票，但处于冷冻期时的最大收益
 * dp[i][2]表示第i天之后手中无股票，不处于冷冻期时的最大收益
 * 那么:
 * 第i天后手中有股票，有可能是一直在手里的，也有可能是第i天买的：dp[i][0] = max(dp[i-1][0], dp[i-1][2]-prices[i])
 * 第i天后无股票并且冷冻期，说明第i天卖了股票：dp[i][1] = dp[i-1][0] + prices[i]
 * 第i天后无股票非冷冻期，说明要不昨天是冷冻期，今天没有买，要不昨天就不在冷冻期也没有股票：dp[i][2] = max(dp[i-1][0], dp[i-1][1])
 *
 * @author: Benjamin
 * @date: 2020-08-15 21:07
 */
public class LeetCode309 {
    /**
     * 思路一：
     * 只关注最后一次买卖的时间，前面若干次买卖视为子问题
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int[] dp = new int[prices.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.max(0, prices[2 - 1] - prices[1 - 1]);

        // 从前往后更新dp
        for (int i = 3; i <= prices.length; i++) {
            int maxValue = Integer.MIN_VALUE;
            // 每次更新dp[i]时，扫描前面的值
            // j为最后一次买卖的开始时间
            for (int j = 1; j < i; j++) {
                if (j == 1 || j == 2) {
                    maxValue = Math.max(maxValue, prices[i - 1] - prices[j - 1]);
                } else {
                    maxValue = Math.max(maxValue, dp[j - 2] + prices[i - 1] - prices[j - 1]);
                }
            }
            // 不进行最后一次买卖
            maxValue = Math.max(maxValue, dp[i - 1]);
            dp[i] = maxValue;
        }
        return dp[prices.length];
    }

    /**
     * 思路二：
     * 关注第i天结束后的状态，有股票、无股票冷冻和无股票不冷冻
     * 这些状态的更新都和 第i-1天结束后的利润+第i天的行为有关系
     * <p>
     * @param prices
     * @return
     */
    public int maxProfit_(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int length = prices.length;

        int[][] dp = new int[length][3];
        // 第0天结束后，只有买了股票，收益会是负，其他都为0
        dp[0][0] = -prices[0];

        for (int i = 1; i < length; i++) {
            // 第i天后手中有股票，有可能是一直在手里的，也有可能是第i天买的：dp[i][0] = max(dp[i-1][0], dp[i-1][2]-prices[i])
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            // 第i天后无股票并且冷冻期，说明第i天卖了股票：dp[i][1] = dp[i-1][0] + prices[i]
            dp[i][1] = dp[i - 1][0] + prices[i];
            // 第i天后无股票非冷冻期，说明要不昨天是冷冻期，今天没有买，要不昨天就不在冷冻期也没有股票：dp[i][2] = max(dp[i-1][0], dp[i-1][1])
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[length - 1][1], dp[length - 1][2]);
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode309().maxProfit_(new int[]{1, 2, 3, 0, 2})); // 3
        System.out.println(new LeetCode309().maxProfit_(new int[]{2, 1})); // 0
        System.out.println(new LeetCode309().maxProfit_(new int[]{1, 2, 4})); // 3
        System.out.println(new LeetCode309().maxProfit_(new int[]{1, 4, 2})); // 3
    }
}
