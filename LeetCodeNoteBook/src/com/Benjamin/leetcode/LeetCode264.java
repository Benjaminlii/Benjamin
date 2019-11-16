package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode264
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * <p>
 * 6 = 2 * 3, 8 = 2 * 2 * 2, 9 = 3 * 3, .......
 *
 * 思路:
 * 使用三个指针,分别只想数组前面的位置,每次用是哪个指针的值分别称2,3,5.将最小的值作为数组中插入的新值
 * 三个指针使用到才向后移动是为了保证不重复,且计算得出的值最小
 *
 * @author: Benjamin
 * @date: 19-11-16 下午4:47
 */
public class LeetCode264 {
    public int nthUglyNumber(int n) {
        int[] flag = new int[n];
        flag[0] = 1;
        int sub = 1, sub2 = 0, sub3 = 0, sub5 = 0;
        while (sub < n) {
            flag[sub] = Math.min(Math.min(flag[sub2] * 2, flag[sub3] * 3), flag[sub5] * 5);
            if (flag[sub] == flag[sub2] * 2) {
                sub2++;
            }
            if (flag[sub] == flag[sub3] * 3) {
                sub3++;
            }
            if (flag[sub] == flag[sub5] * 5) {
                sub5++;
            }
            sub++;
        }
        return flag[--sub];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode264().nthUglyNumber(10));
    }
}