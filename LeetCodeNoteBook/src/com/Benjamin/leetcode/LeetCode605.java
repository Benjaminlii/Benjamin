package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode605
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 *
 * @author: Benjamin
 * @date: 19-10-29 下午5:58
 */
public class LeetCode605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }

        int[] flowerbedCopy = new int[flowerbed.length + 2];
        flowerbedCopy[0] = 0;
        flowerbedCopy[flowerbedCopy.length - 1] = 0;
        System.arraycopy(flowerbed, 0, flowerbedCopy, 1, flowerbed.length);

        for (int i = 1; i < flowerbedCopy.length - 1; i++) {
            if (flowerbedCopy[i - 1] == 0 && flowerbedCopy[i] == 0 && flowerbedCopy[i + 1] == 0) {
                flowerbedCopy[i] = 1;
                n--;
                if (n == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode605().canPlaceFlowers(new int[]{0,1,0,1}, 0));
    }
}
