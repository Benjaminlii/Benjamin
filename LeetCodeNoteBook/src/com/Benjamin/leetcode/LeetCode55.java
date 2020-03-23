package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode55
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * <p>
 * 思路:
 * 使用数组记录可以到达的位置,标为true
 * 遍历原数组,当这个位置可以访问时,根据这个位子上的数字更新可到达位置
 * 优化:使用最大下标即可记录最大达到的位置,一旦当前下标处不可访问,那么后面都不可访问了.
 *
 * @author: Benjamin
 * @date: 20-3-23 下午10:00
 */
public class LeetCode55 {
    public boolean canJump(int[] nums) {
        int maxSub = 0;
        for (int i = 0; i < nums.length && i <= maxSub; i++) {
            maxSub = maxSub > i + nums[i] ? maxSub : i + nums[i];
        }
        return maxSub >= nums.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode55().canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new LeetCode55().canJump(new int[]{3, 2, 1, 0, 4}));
    }
}
