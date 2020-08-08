package com.Benjamin.leetcode;

import java.util.*;

/**
 * ClassName:LeetCode17
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * 思路：
 * 多路递归吧，这样代码简单点
 *
 * @author: Benjamin
 * @date: 2020-08-07 14:57
 */
public class LeetCode17 {
    private Map<Integer, List<Character>> map;

    {
        map = new HashMap<>();
        map.put(2, Arrays.asList('a', 'b', 'c'));
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));
        map.put(5, Arrays.asList('j', 'k', 'l'));
        map.put(6, Arrays.asList('m', 'n', 'o'));
        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        map.put(8, Arrays.asList('t', 'u', 'v'));
        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return ans;
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : map.get(digits.charAt(0) - '0')) {
            ans.addAll(method(sb.append(character), digits, 1));
            sb.deleteCharAt(sb.length() - 1);
        }
        return ans;
    }

    public List<String> method(StringBuilder stringBuilder, String digits, int sub) {
        List<String> ans = new ArrayList<>();
        if (sub == digits.length()) {
            ans.add(stringBuilder.toString());
            return ans;
        }
        List<Character> characterList = map.get(digits.charAt(sub) - '0');
        for (Character character : characterList) {
            ans.addAll(method(stringBuilder.append(character), digits, sub + 1));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode17().letterCombinations("23"));
        System.out.println();
        System.out.println(new LeetCode17().letterCombinations(""));
        System.out.println();
        System.out.println(new LeetCode17().letterCombinations("2"));
    }
}
