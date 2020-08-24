package com.Benjamin.exam;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * ClassName:IQiYi20200823_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 15:31
 */
public class IQiYi20200823_1 {
    private static int method(int num) {
        BigInteger number = getNum(num);
        String s = number.toString();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                break;
            }
            count++;
        }
        System.out.println(s);
        return count;
    }

    private static BigInteger getNum(int num) {
        BigInteger bg = new BigInteger(num + "");
        BigInteger ans = new BigInteger("1");
        BigInteger one = new BigInteger("1");
        for (int i = 0; i < num; i++) {
            ans = ans.multiply(bg);
            bg = bg.subtract(one);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.close();
        System.out.println(method(num));
    }
}
