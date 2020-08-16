package com.Benjamin.leetcode;

import java.util.Stack;

/**
 * ClassName:LeetCode394
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * 思路一：
 * 目测分治
 * 定义一个方法返回一个字符串解码后重复n次的结果
 * 在内部遇到编码后字符串调用自身即可
 * 思路二：
 * 栈，类似括号匹配的题
 *
 * @author: Benjamin
 * @date: 2020-08-16 20:59
 */
public class LeetCode394 {

    /**
     * 思路一
     * 分治递归
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        return decodeString(s, 1);
    }

    public String decodeString(String s, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                // 遇到数字
                // 处理数字部分
                int number = 0;
                while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    number *= 10;
                    number += s.charAt(i) - '0';
                    i++;
                }
                // 抽取子编码串
                i++;
                // 处理子串中的左右中括号
                int countOfLeft = 0;
                StringBuilder sb1 = new StringBuilder();
                while (s.charAt(i) != ']' || countOfLeft != 0) {
                    if (s.charAt(i) == '[') {
                        countOfLeft++;
                    } else if (s.charAt(i) == ']') {
                        countOfLeft--;
                    }
                    sb1.append(s.charAt(i));
                    i++;
                }
                sb.append(decodeString(sb1.toString(), number));
            } else {
                sb.append(s.charAt(i));
            }
        }
        //重复拼接到结果num次
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < num; i++) {
            ans.append(sb);
        }
        return ans.toString();
    }

    /**
     * 思路二
     * 用栈
     *
     * @param s
     * @return
     */
    public String decodeString_(String s) {
        String str = "[" + s + "]";
        char[] chars = str.toCharArray();
        Stack<StringBuilder> sbStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        intStack.push(1);
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            // 数字
            if (ch <= '9' && ch >= '0') {
                // 遇到数字
                // 处理数字部分
                int number = 0;
                while (chars[i] >= '0' && chars[i] <= '9') {
                    number *= 10;
                    number += chars[i] - '0';
                    i++;
                }
                intStack.push(number);
                i--;
            } else if (ch == '[') {
                // 左中括号直接入栈
                sbStack.push(new StringBuilder().append(ch));
            } else if (ch == ']') {
                // 遇到括号说明前面要进行一次字符串的解码了
                // 向前寻找第一个'['之前的所有栈帧
                // 头插法组成要进行重复拼接的字符串
                StringBuilder thisString = new StringBuilder();
                while (!"[".equals(sbStack.peek().toString())) {
                    thisString.insert(0, sbStack.pop());
                }
                // 将前一个'['弹栈
                sbStack.pop();

                // 进行重复拼接
                StringBuilder ans = new StringBuilder();
                int num = intStack.pop();
                for (int j = 0; j < num; j++) {
                    ans.append(thisString);
                }
                sbStack.push(ans);
            } else {
                // 其他的情况就是遇到一般字符
                StringBuilder thisString = new StringBuilder();
                while (chars[i] >= 'A' && chars[i] <= 'Z' || chars[i] >= 'a' && chars[i] <= 'z') {
                    thisString.append(chars[i]);
                    i++;
                }
                sbStack.push(thisString);
                i--;
            }
        }
        return sbStack.pop().toString();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode394().decodeString("3[a]2[bc]"));
        System.out.println(new LeetCode394().decodeString_("3[a]2[bc]"));
        System.out.println(new LeetCode394().decodeString("3[a2[c]]"));
        System.out.println(new LeetCode394().decodeString_("3[a2[c]]"));
        System.out.println(new LeetCode394().decodeString("2[abc]3[cd]ef"));
        System.out.println(new LeetCode394().decodeString_("2[abc]3[cd]ef"));
        System.out.println(new LeetCode394().decodeString("abc3[cd]xyz"));
        System.out.println(new LeetCode394().decodeString_("abc3[cd]xyz"));
    }
}
