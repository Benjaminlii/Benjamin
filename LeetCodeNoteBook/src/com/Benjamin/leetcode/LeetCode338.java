package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode338
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 思路:
 * 一个偶数和其二分之一的数的二进制1的数量是一样的(乘而就是左移一位)
 * 一个奇数为其二分之一的数的二进制1的数量加一(乘二的基础上加一)
 * 以上面为递推动态规划
 * dp[0] = 0
 *
 * @author: Benjamin
 * @date: 20-2-9 上午11:57
 */
public class LeetCode338 {
    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i%2 == 0){
                dp[i] = dp[i/2];
            }else{
                dp[i] = dp[i/2]+1;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LeetCode338().countBits(0)));
        System.out.println(Arrays.toString(new LeetCode338().countBits(2)));
        System.out.println(Arrays.toString(new LeetCode338().countBits(5)));
    }
}
