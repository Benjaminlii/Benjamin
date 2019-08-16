package com.Benjamin.leetcode;

import java.util.*;

/**
 * ClassName:LeetCode687
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 思路:找到众数,并找到其第一次和最后一次出现的下标,做差返回即可
 *
 * @author: Benjamin
 * @date: 19-8-16 下午1:48
 */
public class LeetCode687 {

    public static void main(String[] args) {
        System.out.println(new LeetCode687().findShortestSubArray(new int[]{1}));
    }

    public int findShortestSubArray(int[] nums) {
        List<Integer> list = new ArrayList();

        for (int num : nums) {
            list.add(num);
        }

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int num = 50000;
        for (Integer integer : list) {
            Integer sumOfOne = map.get(integer);
            if (sumOfOne != null) {
                map.put(integer, ++sumOfOne);
            } else {
                map.put(integer, 1);
                sumOfOne = 1;
            }
            if (sum < sumOfOne) {
                sum = sumOfOne;
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == sum) {
//                System.out.println("num = " + key + ":" + num + " >>> " + (list.lastIndexOf(key) - list.indexOf(key) + 1));
                num = Math.min(num,
                        list.lastIndexOf(key) - list.indexOf(key) + 1);
            }
        }

        return num;
    }
}
