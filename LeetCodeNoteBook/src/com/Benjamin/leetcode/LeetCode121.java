package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode121
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 思路:
 * 每次将当前卖出和之前的最小买入进行做差,跟之前的记录值进行比较,大于则替换
 *
 * @author: Benjamin
 * @date: 19-11-10 下午2:06
 */
public class LeetCode121 {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1)
            return 0;
        int minNumber = prices[0],flag = 0;
        for (int i = 1; i < prices.length; i++) {
            flag = Math.max(flag, prices[i]-minNumber);
            minNumber = Math.min(minNumber, prices[i]);
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode121().maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
