package com.Benjamin.exam;

import java.util.*;

/**
 * ClassName:MeiTuan20200815_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 小团最近对逆序数（将一个数字逐位逆序，例如1234的逆序数为4321）特别感兴趣，但是又觉得普通的逆序数问题有点太乏味了。
 * 于是他想出了一个新的定义：如果一个数的4倍恰好是它的逆序数，那么称这两个数是新定义下的逆序对。
 * 接下来给定一正整数n，问：不超过n的正整数中有多少对新定义下的逆序对？
 * <p>
 * 输入描述
 * 单组输入。
 * 输入一个正整数n，n<1e7。
 * 输出描述
 * 第一行输出在不超过n的前提下有多少对逆序数，接下来每一行输出一对逆序数，以空格分隔。如果有多组逆序数，按照第一个数升序输出。
 * 如果没有一对逆序数则直接输出0即可。
 * <p>
 * 样例输入
 * 10000
 * 样例输出
 * 1
 * 2178 8712
 * <p>
 * 提示
 * 在本题目的情景中我们认为：1234的逆序数为4321，1100的逆序数为11
 *
 * @author: Benjamin
 * @date: 2020-08-15 16:02
 */
public class MeiTuan20200815_1 {

    public static List<String> method(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= num / 4; i++) {
            int iX4 = i * 4;
            if (isOk(i, iX4)) {
                list.add(i + " " + iX4);
            }
        }
        return list;
    }

    public static boolean isOk(int num, int numX4) {
        String numString = String.valueOf(num);
        String numX4String = String.valueOf(numX4);

        if (numString.length() != numX4String.length()) {
            return false;
        }
        for (int i = 0; i < numString.length(); i++) {
            if (numString.charAt(i) != numX4String.charAt(numX4String.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main1(String[] args) {
        System.out.println(isOk(2178, 8712));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> ans = method(in.nextInt());
        System.out.println(ans.size());
        if (ans.size() > 0) {
            for (String an : ans) {
                System.out.println(an);
            }
        }
        in.close();
    }
}
