package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:KeDaXunFei20200829_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-29 19:03
 */
public class KeDaXunFei20200829_1 {
    private static int method(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int num1 = i > 0 ? array[i - 1][j] : 0;
                int num2 = j > 0 ? array[i][j - 1] : 0;
                array[i][j] += Math.max(num1, num2);
            }
        }
        return array[array.length - 1][array[0].length - 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] ss = s.split(",");
        int m = Integer.parseInt(ss[0]);
        int n = Integer.parseInt(ss[1]);
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = in.nextInt();
            }
        }
        in.close();

        System.out.println(method(array));
    }
}
