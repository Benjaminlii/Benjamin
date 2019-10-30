package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode213
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * 示例 1:
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 思路:
 * 分两条路走,一条偷第一间房间,不偷最后一间
 * 另一条相反
 * 在每一条路中只需要判断眼前的3个房间,编号为0,1,2
 * 每个房间房间记录下走到这个房间之前偷到的最大金额
 * 需要从0+2 和 1的金额之间选出最大的填入2
 * 向下循环即可
 *
 * @author: Benjamin
 * @date: 19-10-30 上午10:31
 */
public class LeetCode213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(dynamic(nums, 0, nums.length - 2), dynamic(nums, 1, nums.length - 1));
    }

    public int dynamic(int[] nums, int start, int end) {
        int[] sums = new int[end - start + 1];
        sums[0] = nums[start];
        sums[1] = Math.max(nums[start+1],nums[start]);
        for (int i = start + 2; i <= end; i++) {
            sums[i - start] = Math.max(sums[i - start - 1], sums[i - start - 2] + nums[i]);
        }
        return sums[end - start];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode213().rob(new int[]{1,3,1,3,100}));
    }

}
