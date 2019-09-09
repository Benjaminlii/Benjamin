package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode209
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * @author: Benjamin
 * @date: 19-9-9 下午7:32
 */
public class LeetCode209 {

    public static void main(String[] args) {
        System.out.println(new LeetCode209().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int i = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (i < nums.length) {
            if (sum + nums[i] < s) {
                sum += nums[i];
                i++;
            } else {
                sum -= nums[start];
                len = Math.min(len, i - start + 1);
//                System.out.println(len + " = min( " + len + ", " + (i - start + 1) + ")");
                start++;
                // 这里先计算len在start++的目的是start++之前是满足sum>=s的,自加之后就不满足了
            }
//            System.out.println("sum = " + sum + ", i = " + i + ", len = " + len + ",start = " + start + ", s = " + s);
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
