package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode198
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 思路:
 * 遍历数组(下标i),判断nums[i-2]+nums[i] 与 nums[i-1] 的大小情况,进行更新即可
 * 这样nums数组就变成了走到对应下标位置时小偷能偷到的最大金额.
 * 提前对传入数组长度小于2的进行判断
 * 然后从下标1开始进行更新(下标1特殊,单独进行)
 *
 * @author: Benjamin
 * @date: 19-11-13 上午11:02
 */
public class LeetCode198 {
    public int rob(int[] nums) {
        if (nums.length <= 1){
            return nums.length == 0 ? 0 : nums[0];
        }

        nums[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i-2]+nums[i], nums[i-1]);
        }

        return nums[nums.length-1];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode198().rob(new int[]{2,7,9,3,1}));
    }
}
