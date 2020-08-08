package com.Benjamin.exam;

/**
 * ClassName:NetEase20200808_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-08 16:03
 */
public class NetEase20200808_3 {

    public static int method(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 10007;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(method(4));
        System.out.println(method(1));
        System.out.println(method(2));
        System.out.println(method(3));
        System.out.println(method(5));
    }
}
