package com.Benjamin.leetcode;

import java.util.Map;

/**
 * ClassName:LeetCode11
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 11. 盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 思路:
 * 双指针法,两边向中间移动
 * 每次都移动一,所以宽永远是减少一
 * 那么选择高比较小的一边进行收缩
 * 因为容器的高度是由低的一边决定的,如果移动高的一侧,那么移动后的容器高度怎么都不会超过这个高的边
 * 而移动低的一侧是有可能获得一个高的边的
 *
 * @author: Benjamin
 * @date: 20-3-6 下午12:00
 */
public class LeetCode11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int weight = height.length - 1;
        int ans = Integer.MIN_VALUE;
        int num;
        while (left < right) {
            num = weight * (Math.min(height[left], height[right]));
            if (ans < num) {
                ans = num;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
            weight--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode11().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));// 49
    }
}
