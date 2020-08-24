package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:Tencent20200823_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 20:33
 */
public class Tencent20200823_3 {
    private static int method(int num) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < num / 2 + 1; i++) {
            int a = s(i) + s(num - i);
            ans = Math.max(a, ans);
        }
        return ans;
    }

    private static int s(int num) {
        int ans = 0;
        while (num != 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            System.out.println(method(in.nextInt()));
        }
    }
}
