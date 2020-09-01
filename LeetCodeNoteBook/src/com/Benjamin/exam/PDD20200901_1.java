package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:PDD20200901_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * case1:
 * input:
 * 4
 * <p>
 * output:
 * 0 2 1 0
 * 3 0 0 8
 * 4 0 0 7
 * 0 5 6 0
 * <p>
 * case2:
 * input:
 * 5
 * <p>
 * output:
 * 0 2 0 1 0
 * 3 0 0 0 8
 * 0 0 0 0 0
 * 4 0 0 0 7
 * 0 5 0 6 0
 *
 * @author: Benjamin
 * @date: 2020-09-01 19:05
 */
public class PDD20200901_1 {
    private static int[][] method(int num) {
        int[][] ans = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ans[i][j] = getNumber(i, j, num);
            }
        }

        return ans;
    }

    private static int getNumber(int sub1, int sub2, int num) {
        int ans = 0;
        if (num % 2 != 0 && (sub1 == num / 2 || sub2 == num / 2)) {
            return ans;
        }
        if (sub1 == sub2 || sub1 + sub2 == num - 1) {
            return ans;
        }
        if (sub1 < num / 2 && sub2 < num / 2) {
            // 23
            if (sub1 > sub2) {
                ans = 3;
            } else {
                ans = 2;
            }
        } else if (sub1 >= num / 2 && sub2 < num / 2) {
            // 45
            sub1 -= num / 2;
            if (sub1 + sub2 < num / 2) {
                ans = 4;
            } else {
                ans = 5;
            }
        } else if (sub1 >= num / 2 && sub2 >= num / 2) {
            // 67
            if (sub1 > sub2) {
                ans = 6;
            } else {
                ans = 7;
            }
        } else if (sub1 < num / 2 && sub2 >= num / 2) {
            // 81
            sub2 -= num / 2;
            if (sub1 + sub2 < num / 2) {
                ans = 1;
            } else {
                ans = 8;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int num = in.nextInt();
        int num = 7;
        in.close();

        int[][] ans = method(num);
        StringBuilder sb = new StringBuilder();
        for (int[] an : ans) {
            for (int i : an) {
                sb.append(i).append(" ");
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

    }
}
