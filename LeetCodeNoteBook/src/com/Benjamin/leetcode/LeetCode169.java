package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode169
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 思路：
 * 这寻常题么，计数，不同减一，相同加一
 * 为0时，更新记录的元素为当前元素
 *
 * @author: Benjamin
 * @date: 2020-08-13 21:18
 */
public class LeetCode169 {
    public int majorityElement(int[] nums) {
        int count = 1;
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                ans = nums[i];
                count = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode169().majorityElement(new int[]{3, 2, 3}));
        System.out.println(new LeetCode169().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(new LeetCode169().majorityElement(new int[]{10, 9, 9, 9, 10}));
    }
}
