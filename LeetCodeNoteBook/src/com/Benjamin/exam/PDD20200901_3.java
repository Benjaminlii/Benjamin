package com.Benjamin.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName:PDD20200901_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-01 19:39
 */
public class PDD20200901_3 {
    private static int method(int[] ci, int[] vi, int num, int count) {
        int size = ci.length;
        // i 只考虑前i的物品
        // j 当前背包容量
        int[][] dp = new int[size][num + 1 + count];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MIN_VALUE);
        }

        for (int i = 0; i <= num; i++) {
            dp[0][i] = vi[0] <= i && ci[0] > 0 ? ci[0] : 0;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < num + 1; j++) {
                // 不新加入物品
                dp[i][j] = dp[i - 1][j];

                // 如果能放的下第i件物品
                if (vi[i] <= j) {
                    // 不放   放
                    dp[i][j] = Math.max(dp[i][j], ci[i] + dp[i - 1][j - vi[i]]);
                }
            }
        }
        return dp[size - 1][num];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int count = 0;

        int[] ci = new int[n];
        int[] vi = new int[n];
        for (int i = 0; i < n; i++) {
            ci[i] = in.nextInt();
            vi[i] = in.nextInt();
            if (ci[i] < 0) {
                count -= ci[i];
            }
        }

        System.out.println(method(vi, ci, m, count));
    }
}
