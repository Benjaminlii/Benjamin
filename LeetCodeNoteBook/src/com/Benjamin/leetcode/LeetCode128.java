package com.Benjamin.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 思路:将所有元素放入集合中,循环遍历,找到num-1不在集合中的数(序列的开始)
 * 然后判断num+1是否存在,num+2是否存在,每次计数,并和最大值比较是否覆盖
 * <p>
 * author:Benjamin
 * date:2019.7.30
 */
public class LeetCode128 {

    public static void main(String[] args) {
        System.out.println(new LeetCode128().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public int longestConsecutive(int[] nums) {
        //最大长度,当前序列长度
        int rtn = 0;
        int count = 1;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (Integer integer : set) {
            if (!set.contains(integer - 1)) {
                count = 1;
                int num = integer;
                while (set.contains(num + 1)) {
                    num++;
                    count++;

                }
                rtn = Math.max(rtn, count);
            }
        }
        return rtn;
    }

}
