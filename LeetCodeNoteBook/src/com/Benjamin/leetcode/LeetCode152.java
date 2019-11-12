package com.Benjamin.leetcode;

import java.util.Map;

/**
 * ClassName:LeetCode152
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @author: Benjamin
 * @date: 19-11-12 上午9:56
 */
public class LeetCode152 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <0){
                imax ^= imin;
                imin ^= imax;
                imax ^= imin;
            }
            imax = Math.max(imax*nums[i],nums[i]);
            imin = Math.min(imin*nums[i],nums[i]);

            max = Math.max(max,imax);

        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode152().maxProduct(new int[]{-3,0,1,-2}));
    }
}
