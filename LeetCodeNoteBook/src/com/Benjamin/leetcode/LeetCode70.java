package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode70
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * @author: Benjamin
 * @date: 19-11-7 上午9:56
 */
public class LeetCode70 {
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        else if (n == 2)
            return 2;
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;

        for (int i = 2; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode70().climbStairs(10));
    }
}