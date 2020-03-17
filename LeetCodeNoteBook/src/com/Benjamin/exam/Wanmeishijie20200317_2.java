package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:Wanmeishijie20200317_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-17 下午8:16
 */
public class Wanmeishijie20200317_2 {
    public static int[][] muthod(int m, int n, int[] w, int[] p) {
        int c[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) {
            c[0][j] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (w[i - 1] <= j) {
                    if (c[i - 1][j] < (c[i - 1][j - w[i - 1]] + p[i - 1])) {
                        c[i][j] = c[i - 1][j - w[i - 1]] + p[i - 1];
                    } else {
                        c[i][j] = c[i - 1][j];
                    }
                }else{
                    c[i][j] = c[i-1][j];
                }
            }
        }
        return c;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int v = in.nextInt();
        int w[] = new int[num];
        int p[] = new int[num];
        for (int i = 0; i < w.length; i++) {
            w[i] = in.nextInt();
        }
        for (int i = 0; i < p.length; i++) {
            p[i] = in.nextInt();
        }

        int[][] ans = muthod(num,v,w,p);
        System.out.println(ans[num][v]);
    }
}
