package com.Benjamin.exam;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * ClassName:Didi20200821_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-21 19:37
 */
public class Didi20200821_1 {
    public static BigInteger[][] method(int n) {
        if (n == 1) {
            return new BigInteger[][]{{new BigInteger("1")}};
        }
        BigInteger[][] ans = new BigInteger[n][n];
        int top = 0;
        int left = 0;
        int right = n - 1;
        int down = n - 1;

        BigInteger[] fib = getFib(n * n);
        int sub = fib.length - 1;

        while (true) {
            for (int i = left; i <= right; i++) {
                ans[top][i] = fib[sub--];
            }
            top++;
            if (top > down) {
                break;
            }

            for (int i = top; i <= down; i++) {
                ans[i][right] = fib[sub--];
            }
            right--;
            if (left > right) {
                break;
            }

            for (int i = right; i >= left; i--) {
                ans[down][i] = fib[sub--];
            }
            down--;
            if (top > down) {
                break;
            }

            for (int i = down; i >= top; i--) {
                ans[i][left] = fib[sub--];
            }
            left++;
            if (left > right) {
                break;
            }
        }

        return ans;
    }

    private static BigInteger[] getFib(int n) {
        BigInteger[] ans = new BigInteger[n];
        ans[0] = new BigInteger("1");
        ans[1] = new BigInteger("1");
        for (int i = 2; i < n; i++) {
            ans[i] = ans[i - 1].add(ans[i - 2]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BigInteger[][] ans = method(n);
        StringBuilder sb = new StringBuilder();
        for (BigInteger[] arr : ans) {
            for (BigInteger i : arr) {
                sb.append(i).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
