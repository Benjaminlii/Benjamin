package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode75
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * @author: Benjamin
 * @date: 19-10-20 下午12:28
 */
public class LeetCode75 {
    public void sortColors(int[] nums) {
        // 三指针
        int i=0, l=0, r=nums.length-1;
        while(i<=r){
            if(i<l || nums[i]==1)
                i++;
            else if(nums[i]==0)
                swap(nums,i,l++);
            else if(nums[i]==2)
                swap(nums,i,r--);
        }
    }

    private void swap(int[] nums, int i, int j){
        if(i==j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,0,2,1,1,0};

        new LeetCode75().sortColors(array);
        System.out.println(Arrays.toString(array));
    }
}
