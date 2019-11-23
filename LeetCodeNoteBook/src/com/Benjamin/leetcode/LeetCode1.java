package com.Benjamin.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:LeetCode1
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 思考:
 * O(n)的解法:使用一个map,编译一遍数组,每次计算出当前遍历到的元素和result相加可以得到target,判断之前有没有得到过result
 * 如果result已经存在在map中,就说明找到了
 * 如果不在,就将当前遍历到的元素放入map
 * 明显的空间换时间
 *
 * @author: Benjamin
 * @date: 19-10-20 下午12:07
 */
public class LeetCode1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int result;
        for (int i = 0; i < nums.length; i++) {
            result = target - nums[i];
            if (map.containsKey(result)) {
                return new int[]{map.get(result), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LeetCode1().twoSum1(new int[]{2, 7, 11, 15}, 9)));
    }
}
