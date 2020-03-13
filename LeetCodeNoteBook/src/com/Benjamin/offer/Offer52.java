package com.Benjamin.offer;

/**
 * ClassName:Offer52
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 正则表达式匹配
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 * @author: Benjamin
 * @date: 20-1-2 下午9:39
 */
public class Offer52 {
    public boolean match(char[] str, char[] pattern) {
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int i, char[] pattern, int j) {
        // pat数组遍历完时str也必须遍历完,否则为匹配失败
        if (j == pattern.length)
            return str.length == i;
        // 带越界检查,因为后面会有j+2的递归,par数组中当前遍历到带*的字符
        if (j < pattern.length - 1 && pattern[j + 1] == '*') {
            // 当前匹配(匹配x*或者.*)
            if ((str.length != i) && ((str[i] == pattern[j] || pattern[j] == '.'))) {
                return match(str, i + 1, pattern, j) || match(str, i, pattern, j + 2);
            } else {// 当前不匹配,那么par数组往后遍历两位
                return match(str, i, pattern, j + 2);
            }
        }
        // par当前遍历到元素不带*,那么进行一般的匹配
        if ((str.length != i) && ((str[i] == pattern[j]) || (pattern[j] == '.'))) {
            return match(str, i + 1, pattern, j + 1);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Offer52().match("bbbba".toCharArray(), ".*a*a".toCharArray()));
    }
}
