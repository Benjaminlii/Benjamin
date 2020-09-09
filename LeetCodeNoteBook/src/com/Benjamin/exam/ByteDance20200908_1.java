package com.Benjamin.exam;

/**
 * ClassName:ByteDance20200908_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 62进制加法
 *
 * @author: Benjamin
 * @date: 2020-09-09 15:26
 */
public class ByteDance20200908_1 {
    private static int getNum(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        }
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 10;
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 10 + 26;
        }
        return 0;
    }

    private static char getCh(int num) {
        if (num >= 0 && num <= 9) {
            return (char) (num + '0');
        }
        if (num >= 10 && num <= 10 + 26) {
            return (char) (num - 10 + 'a');
        }
        if (num >= 10 + 26 + 1 && num <= 61) {
            return (char) (num - 20 - 26 + 'A');
        }
        return '\0';
    }

    private static String method(String str1, String str2) {
        int flag = 0;
        StringBuilder ans = new StringBuilder();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int num = 1;
        while (chars1.length - num >= 0 || chars2.length - num >= 0) {
            int num1 = 0;
            int num2 = 0;
            if (chars1.length - num >= 0) {
                num1 = getNum(chars1[chars1.length - num]);
            }
            if (chars2.length - num >= 0) {
                num2 = getNum(chars2[chars2.length - num]);
            }
            int number = num1 + num2 + flag;
            if (number >= 62) {
                flag = 1;
                number %= 62;
            } else {
                flag = 0;
            }

            ans.insert(0, getCh(number));
            num++;
        }
        if (flag != 0) {
            ans.insert(0, flag);
        }



        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(method("a", "2"));
        System.out.println(method("19", "1"));
        System.out.println(method("Z", "1"));
        System.out.println(method("ZZZ", "1"));
    }
}
