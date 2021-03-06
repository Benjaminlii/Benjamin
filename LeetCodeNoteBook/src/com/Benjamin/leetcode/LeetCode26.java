package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode26
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * @author: Benjamin
 * @date: 19-10-27 上午10:30
 */
public class LeetCode26 {
    public int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[count]) {
                nums[i] = Integer.MAX_VALUE;
                continue;
            }
            count++;
            swap(nums, i, count);
        }
        return count + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode26().removeDuplicates(new int[]{1, 1, 1, 1, 2, 2, 2, 3, 4, 5, 5, 5, 6, 6, 6, 6, 7}));
    }
}
