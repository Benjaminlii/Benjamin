package com.Benjamin.leetcode;


public class Test {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        System.out.println("[" + nums[0] + ", " + nums[1] + "]");

        swap(nums, 0, 1);
        System.out.println("[" + nums[0] + ", " + nums[1] + "]");
    }

    public static void swap(int[] nums, int sub1, int sub2){
        int tmp = nums[sub1];
        nums[sub1] = nums[sub2];
        nums[sub2] = tmp;
    }
}
