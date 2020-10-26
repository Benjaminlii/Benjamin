package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode581
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 * <p>
 * 思路：
 * 从中间区域走向两边时，维护一个min和一个max，当下一个元素小于或大于这个最值的时候，说明找到了边界
 *
 * @author: Benjamin
 * @date: 2020-10-26 17:18
 */
public class LeetCode581 {
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;

        int min = nums[length - 1];
        int max = nums[0];

        int start = 0, end = -1;
        for (int i = 0; i < length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
            if (nums[length - 1 - i] <= min) {
                min = nums[length - 1 - i];
            } else {
                start = length - 1 - i;
            }
        }

        return end - start + 1;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode581().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new LeetCode581().findUnsortedSubarray(new int[]{1, 2, 3, 4}));
    }
}
