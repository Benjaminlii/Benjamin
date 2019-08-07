package com.Benjamin.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * author:Benjamin
 * date:2019.8.7
 */
public class LeetCode349 {

    public static void main(String[] args) {
        int nums1[] = {4,9,5};
        int nums2[] = {9,4,9,8,4};
        int answer[] = new LeetCode349().intersection(nums1, nums2);
        System.out.print("[");
        for (int i : answer) {
            System.out.print(i + " ");
        }
        System.out.println("]");

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> numSet2 = new HashSet<>();
        Set<Integer> rtnSet = new HashSet<>();
        for (int i : nums2) {
            numSet2.add(i);
        }

        for (int i : nums1) {
            if(numSet2.contains(i)){
                rtnSet.add(i);
            }
        }

        int [] rtn = new int[rtnSet.size()];
        int i = 0;
        for (int s : rtnSet){
            rtn[i++] = s;
        }
        return rtn;
    }
}
