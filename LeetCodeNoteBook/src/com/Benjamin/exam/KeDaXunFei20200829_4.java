package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:KeDaXunFei20200829_4
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-29 19:35
 */
public class KeDaXunFei20200829_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = 2;
        while (k <= n) {
            if (k == n) {
                System.out.println(n);
                break;
            } else if (n % k == 0) {
                System.out.print(k + "*");
                n = n / k;
            } else {
                k++;
            }
        }
    }
}
