package com.Benjamin.exam;

import java.util.Arrays;

/**
 * ClassName:KuaiShou20200322_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-22 下午7:04
 */
public class KuaiShou20200322_1 {
    /**
     * 获取队中从前到后每个人与前方身高高于自己的人的最短距离
     *
     * @param height int整型一维数组 队中从前到后每个人与前方身高高于自己的人的最短距离
     * @return int整型一维数组
     */
    public int[] DistanceToHigher(int[] height) {
        int[] array = new int[height.length];
        for (int i = 0; i < array.length; i++) {
            int num = 0;
            for (int j = 0; j < i; j++) {
                if (height[j] > height[i]) {
                    num = i - j;
                }
            }
            array[i] = num;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new KuaiShou20200322_1().DistanceToHigher(new int[]{175, 173, 174, 163, 182, 177})));
    }
}