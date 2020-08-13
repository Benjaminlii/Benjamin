package com.Benjamin.exam;

import java.util.Arrays;

/**
 * ClassName:Bilibili20200813_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-13 20:18
 */
public class Bilibili20200813_2 {
    public static int method(int[] array) {
        int[] dp = new int[array.length];
        // 全部填充为1
        Arrays.fill(dp, 1);

        // 遍历n次，每次都更新数组中每一个元素的权重（左右关系：大于左右元素则更新）
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int leftNum = Integer.MIN_VALUE;
                int rightNum = Integer.MIN_VALUE;
                if (j != 0) {
                    // 大于左侧元素则为左侧元素的权重，否则为MIN_VALUE
                    leftNum = array[j] > array[j - 1] ? dp[j - 1] : leftNum;
                }
                if (j != array.length - 1) {
                    // 同上
                    rightNum = array[j] > array[j + 1] ? dp[j + 1] : rightNum;
                }
                // 左右选择大的元素+1进行更新
                // 为了避免左右都是MIN_VALUE的情况下，更新为MIN_VALUE+1，外面再加一层和1判断大小
                dp[j] = Math.max(1, Math.max(leftNum, rightNum) + 1);
            }
        }
        // 求和得到答案
        int count = 0;
        for (int num : dp) {
            count += num;
        }

        return count;
    }

    public static int method1(int[] array) {
        int[] dp = new int[array.length];
        // 全部填充为1
        Arrays.fill(dp, 1);

        // 从前向后遍历，更新单调递增的子序列
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        // 从后向前遍历，更新单调递减的子序列
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] > array[i + 1]) {
                dp[i] = dp[i + 1] + 1;
            }
        }
        // 求和得到答案
        int count = 0;
        for (int num : dp) {
            count += num;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(method(new int[]{2, 1, 2, 2, 3}));
        System.out.println(method1(new int[]{2, 1, 2, 2, 3}));
    }
}
