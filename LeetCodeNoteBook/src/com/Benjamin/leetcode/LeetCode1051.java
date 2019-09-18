package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode1051
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
 * 请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
 * <p>
 * 思路:
 * 对给定数组中的元素进行计数,使用下标当做元素,指向的内容当做出现的次数
 * 计数完成后就可以得到排序后的序列(按顺序列出来)
 * 与原数组进行比较,不同的地方计数
 *
 * @author: Benjamin
 * @date: 19-9-18 下午9:05
 */
public class LeetCode1051 {

    public static void main(String[] args) {
        System.out.println(new LeetCode1051().heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
    }

    public int heightChecker(int[] heights) {
        int array[] = new int[101];
        int count = 0;
        for (int height : heights) {
            array[height]++;
        }
        for (int i = 0, j = 0; i < array.length; i++) {//i为当前遍历的数值,j为原数组中遍历到的下标
            while (array[i]-- > 0) {//标记数组中对应位置上不为空
                if (heights[j++] != i) {
                    count++;
                }
            }
        }

        return count;
    }
}
