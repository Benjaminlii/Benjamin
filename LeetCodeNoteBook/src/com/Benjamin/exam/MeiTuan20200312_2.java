package com.Benjamin.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName:MeiTuan20200312_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 题目描述：
 * 给出一个序列包含n个正整数的序列A，然后给出一个正整数x，你可以对序列进行任意次操作的，每次操作你可以选择序列中的一个数字，让其与x做按位或运算。
 * 你的目的是让这个序列中的众数出现的次数最多。
 * 请问众数最多出现多少次。
 * 输入
 * 输入第一行仅包含两个正整数n和x，表示给出的序列的长度和给定的正整数。(1<=n<=100000,1<=x<=1000)
 * 接下来一行有n个正整数，即这个序列，中间用空格隔开。(1<=a_i<=1000)
 * 输出
 * 输出仅包含一个正整数，表示众数最多出现的次数。
 * 样例输入
 * 5 2
 * 3 1 3 2 5
 * 样例输出
 * 3
 *
 * @author: Benjamin
 * @date: 20-3-12 下午7:54
 */
public class MeiTuan20200312_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int a[] = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt();
        }
        same(a, n);
    }

    public static void same(int[] a, int n) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] | n;
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = Biannary2Decimal(b[i]);
        }
        Arrays.sort(b);
        System.out.println(b[b.length / 2]);
    }

    public static Integer Biannary2Decimal(int bi) {
        String binStr = bi + "";
        Integer sum = 0;
        int len = binStr.length();
        for (int i = 1; i <= len; i++) {
            int dt = Integer.parseInt((binStr.substring(i - 1, i)));
            sum += (int) Math.pow(2, len - i) * dt;
        }
        return sum;
    }
}
