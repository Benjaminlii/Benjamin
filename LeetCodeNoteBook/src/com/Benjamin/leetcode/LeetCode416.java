package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode416
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * 思路:
 * 将一个数组分为两个和相等的子集,那么可以转化为在一个数组中找到和为整个数组元素和一半的一个子集
 * 那么可以使用动态规划求解
 * dp[i]表示当前数组中是否可以找出和为i的子集
 *
 * @author: Benjamin
 * @date: 20-2-8 上午11:47
 */
public class LeetCode416 {
    public boolean canPartition(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        if (count % 2 != 0) {
            return false;
        }
        count /= 2;

        boolean[] dp = new boolean[count + 1];
        dp[0] = true;
        // 在外层遍历元素避免重复使用
        for (int num : nums) {
            // 从后往前遍历避免一个元素在一次判断中重复使用
            for (int i = count; i >= 0; i--) {
                if (i - num >= 0 && dp[i - num]) {
                    dp[i] = true;
                }
            }
        }
        return dp[count];
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode416().canPartition(new int[]{1, 5, 11, 5}));// true
        System.out.println(new LeetCode416().canPartition(new int[]{1, 2, 3, 5}));// false
        System.out.println(new LeetCode416().canPartition(new int[]{1, 2, 5}));// false
    }
}
