package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode91
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * @author: Benjamin
 * @date: 19-11-8 上午9:24
 */
public class LeetCode91 {
    public int numDecodings(String s) {
        if (s.length() < 2) {
            return s.charAt(0) == '0' ? 0 : 1;
        }
        int[] flag = new int[s.length()];
        int num = getNumber(s.charAt(0), s.charAt(1));
        flag[0] = s.charAt(0) == '0' ? 0 : 1;
        flag[1] = num > 0 && num < 27 ? flag[0]+1 : 1;
        for (int i = 2; i < s.toCharArray().length; i++) {
            num = getNumber(s.charAt(i - 1), s.charAt(i));
            flag[i] += flag[i - 1];
            if (num > 0 && num < 27) {
                flag[i] += flag[i - 2];
            }
        }
        return flag[flag.length - 1];
    }

    public static int getNumber(char a, char b) {
        return 10 * (a - '0') + b - '0';
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode91().numDecodings("010"));
    }
}
