package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode475
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 * <p>
 *
 * @author: Benjamin
 * @date: 19-9-27 下午3:02
 */
public class LeetCode475 {

    public static void main(String[] args) {
        System.out.println(new LeetCode475().findRadius(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 4}));
    }

    public int findRadius(int[] houses, int[] heaters) {
        // 先进行升序排列
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        for (int house : houses) {
            while (i < heaters.length && heaters[i] < house) {
                // 一直找到处于房屋右侧的热水器
                i++;
            }
            if (i == 0)
                radius = Math.max(radius, heaters[i] - house);
            else if (i == heaters.length)
                // 最后一个热水器
                return Math.max(radius, houses[houses.length-1] - heaters[heaters.length-1]);
            else
                // 房屋右侧的热水器和房屋左侧的热水器，取小的那个
                radius = Math.max(radius, Math.min(heaters[i] - house, house - heaters[i - 1]));
        }
        return radius;
    }

}
