package com.Benjamin.leetcode;


/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例:
 * 给定 nums = [1,1,1,2,2,3],
 * <p>
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 思路:
 * 使用额外的O(1)的空间存放当前遍历到元素出现的的次数
 * count超出2的后面所有元素向前移动count-2次
 * <p>
 * author:Benjamin
 * date:2019.7.28
 */
public class LeetCode80 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4};
        int length;
        System.out.println(length = new LeetCode80().removeDuplicates(nums));
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }
    }

    public int removeDuplicates(int[] nums) {
        int count;
        int length = nums.length;
        count = 1;
        for (int i = 1; i < length; i++) {
//            System.out.println("i = " + i + ", count = " + count);
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                if (count > 2) {
                    move(nums, i, count - 2);
                    length -= count - 2;
                    i -= count - 2;
                }
                count = 1;
            }
            if (i == length - 1 && count > 2) {
                move(nums, i, count - 2);
                length -= count - 2;
                i -= count - 2;
            }
        }

        return length;
    }

    /**
     * 移动数组
     *
     * @param nums  需要移动的数组
     * @param sub   移动的序列的起始下标
     * @param count 要向前移动的距离
     */
    public static void move(int[] nums, int sub, int count) {
//        System.out.println(">>> sub = " + sub + ", count = " + count);
        while (sub < nums.length) {
            nums[sub - count] = nums[sub];
            sub++;
        }
    }


    /**
     * 一个比较好的解法,相当于使用了双指针.
     */
    public int removeDuplicates1(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

}
