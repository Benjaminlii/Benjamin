package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:JingDong20200827_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-27 19:37
 */
public class JingDong20200827_2 {
    private static int method(int[][] arrays, int i, int j) {
        if (i == arrays.length - 1) {
            return arrays[i][j];
        }
        int ans = Integer.MIN_VALUE;
        ans = Math.max(method(arrays, i + 1, j - 1), ans);
        ans = Math.max(method(arrays, i + 1, j), ans);
        ans = Math.max(method(arrays, i + 1, j + 1), ans);
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int high = in.nextInt();
        int[][] arrays = new int[high][2 * high - 1];
        for (int i = 0; i < high; i++) {
            // start:lenght-1-i  count:2^(n+1)-1
            int start = high - 1 - i;
            int num = 2 * i + 1;
            for (int j = 0; j < num; j++) {
                arrays[i][start + j] = in.nextInt();
            }
        }
        System.out.println(method(arrays, 0, high - 1));
    }
}
