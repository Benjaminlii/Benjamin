package com.Benjamin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:LeetCode522
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定字符串列表，你需要从它们中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
 * 输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。
 * <p>
 * 思路:
 * 题意就是找到给定字符串数组中最大并且不是其他字符串子串的子串
 * 先筛除其中重复出现的,然后从长到短一个一个判断,遍历完数组返回-1
 *
 * @author: Benjamin
 * @date: 19-10-22 下午2:59
 */
public class LeetCode522 {
    public int findLUSlength(String[] strs) {
        // 先去重复
        Map<String, Integer> times = new HashMap<>();
        for (String str : strs) {
            if (times.containsKey(str)) {
                times.put(str, times.get(str) + 1);
            } else {
                times.put(str, 1);
            }
        }

        // 从长到短一个一个判断
        for (int i = 10; i > 0; i--) {
            for (String s : times.keySet()) {
                if (s.length() == i && times.get(s) == 1) {
                    boolean flag = true;
                    for (String ss : strs) {
                        // 长度小于当前串长度肯定不是子串
                        System.out.println("s = " + s + ", ss = " + ss);

                        if (ss.length() > i && check(s, ss)) {
                            flag = false;
                        }
                    }
                    if (flag){
                        return i;
                    }
                }
            }
        }
        return -1;
    }


    // s是否是ss的子串
    public boolean check(String s, String ss) {
        int length = s.length();
        for (int i = 0; i <= ss.length() - length; i++) {
//            System.out.println(">>>" + ss.substring(i, i + length));
            if (s.equals(ss.substring(i, i + length))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode522().findLUSlength(new String[]{"aabbcc", "aabbcc","cb","abc"}));
    }
}
