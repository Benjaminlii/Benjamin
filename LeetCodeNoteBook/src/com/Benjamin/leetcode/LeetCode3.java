package com.Benjamin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:LeetCode3 Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 思路:
 * 滑动窗口
 * 使用map存储某一个元素最后一次出现的下标
 * 当窗口右侧为重复元素时,窗口左侧调整为之前出现过的重复元素的后继元素
 * 每层循环更新一次ans
 * <p>
 * 好久没刷题了,错误连篇.
 *
 * @author: Benjamin
 * @date: 20-2-4 下午4:33
 */
public class LeetCode3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }

        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        int ans = 0;
        int i = 0, j = 0;

        while (i < s.length() && j < s.length()) {
            if (!map.containsKey(chars[j])) {
                map.put(chars[j], j);
            } else {
                i = Math.max(map.get(chars[j]) + 1, i);
                map.put(chars[j], j);
            }
            ans = Math.max(ans, j - i + 1);

            j++;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode3().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LeetCode3().lengthOfLongestSubstring("pwwkew"));
    }
}
