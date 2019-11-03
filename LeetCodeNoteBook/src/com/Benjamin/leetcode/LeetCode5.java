package com.Benjamin.leetcode;

import java.util.*;

/**
 * ClassName:LeetCode5
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * @author: Benjamin
 * @date: 19-11-3 上午9:59
 */
public class LeetCode5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Queue<Integer[]> queue = new LinkedList<>();
        String ans = "";

        for (int i = 0; i < s.toCharArray().length; i++) {
            queue.add(new Integer[]{i, i});
        }
        ans = s.substring(0,1);
        for (int i = 0; i < s.toCharArray().length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                queue.add(new Integer[]{i, i + 1});
                if (ans.length() < 2) {
                    ans = s.substring(i, i + 2);
                }
            }
        }

        while (queue.size() != 0) {
            Integer[] integers = queue.poll();
            int i = integers[0] - 1;
            int j = integers[1] + 1;
            if (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                queue.add(new Integer[]{i,j});
                if (ans.length() < j-i+1) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode5().longestPalindrome("litaooatil"));
    }
}
