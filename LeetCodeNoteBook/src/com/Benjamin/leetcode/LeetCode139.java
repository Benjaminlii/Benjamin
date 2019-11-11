package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode139
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-11-11 下午4:59
 */
public class LeetCode139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] flags = new boolean[s.length() + 1];
        flags[0] = true;
        for (int i = 1; i < flags.length; i++) {
            for (int j = 0; j < i; j++) {
                if (flags[j] && wordDict.contains(s.substring(j,i))){
                    flags[i] = true;
                    break;
                }
            }
        }
        return flags[flags.length-1];
    }

    public static void main(String[] args) {
        String s = "applepenaapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");

        System.out.println(new LeetCode139().wordBreak(s,wordDict));
    }
}
