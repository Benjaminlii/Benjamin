package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode162
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 * 时间复杂度要求O(logN)
 * 思路:
 * 遍历数组肯定可以得到一个峰值的,凡是时间复杂度是O(N)
 * 采用二分查找,在二分的地方向数值趋于增大的方向查找,一定可以找到一个峰值
 *
 * @author: Benjamin
 * @date: 19-10-19 上午9:02
 */
public class LeetCode162 {

    public static void main(String[] args) {
        System.out.println(new LeetCode162().findPeakElement(new int[]{1,2,3,1}));
    }

    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        int mid = 0;
        while (start<end){
            mid = (start+end)/2;
            if (nums[mid] > nums[mid+1]){
                // mid左侧出现峰值
                end = mid;
            }else{
                start = mid+1;
            }
        }
        return start;
    }
}
