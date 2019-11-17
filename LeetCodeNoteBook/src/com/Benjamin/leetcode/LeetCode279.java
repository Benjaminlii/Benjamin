package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode279
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * <p>
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * <p>
 * 思路:
 * 每一个数字都可以看成是前面一个数字加一个平方数,这一个平方数只占一
 * 0为0
 * dp[1]为dp[0]+1=1  平方数是0
 * dp[2]为dp[1]+1=2  平方数是0
 * dp[3]为dp[2]+1=3  平方数是0
 * 从dp[4]开始有了变化
 * dp[4]为dp[0]+1=1  平方数是4
 *
 * @author: Benjamin
 * @date: 19-11-17 下午5:08
 */
public class LeetCode279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = i;
            int pow;
            for (int j = 0; (pow = j * j) <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - pow] + 1);
            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode279().numSquares(12));
    }
}
