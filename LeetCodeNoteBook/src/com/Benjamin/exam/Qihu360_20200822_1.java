package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:Qihu360_20200822_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-22 20:38
 */
public class Qihu360_20200822_1 {

    private static boolean method(String s) {
        if (s.length() > 10) {
            return false;
        }
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int count = 0;
        for (int i = 0; i < n; i++) {
            boolean ans = method(in.nextLine());
            count = ans ? count + 1 : count;
        }
        System.out.println(count);
    }
}
