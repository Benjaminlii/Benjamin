package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:Baidu20200329_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-29 下午7:22
 */
public class Baidu20200329_1 {
    private static int min(int a, int b) {
        return a * b / max(a, b);
    }

    private static int max(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        if (a % b == 0) {
            return b;
        }
        return max(a - b, b);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int num = max(i, j);
                max = Math.max(max, (i * j - num * num) / num);
            }
        }
        System.out.println(max);
    }
}
