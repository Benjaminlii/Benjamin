package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode300
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 思路:
 * 还是使用动态规划
 * dp[i]表示以nums[i]元素结尾的最长上升子序列长度
 * 那么,每次只需要在0~i之间找出满足nums[j]<nums[i]的dp[j]长度,取其中的最大值,再加一就是dp[i]的值
 * 然后在这个过程中记录dp[i]的最大值,返回即可.
 *
 * @author: Benjamin
 * @date: 20-3-14 下午5:01
 */
public class LeetCode300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            // beforeMaxVal初始为0,表示如果前面没有比nums[i]晓得值的话
            // 以nums[i]结尾的上升子序列前缀为长度0
            int beforeMaxVal = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    beforeMaxVal = Math.max(beforeMaxVal, dp[j]);
                }
            }
            dp[i] = beforeMaxVal + 1;
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode300().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
