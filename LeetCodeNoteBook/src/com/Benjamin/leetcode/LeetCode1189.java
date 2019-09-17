package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode1189
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 * @author: Benjamin
 * @date: 19-9-17 下午3:23
 */
public class LeetCode1189 {
    public static void main(String[] args) {
        System.out.println(new LeetCode1189().maxNumberOfBalloons("loonbalxballpoon"));
    }

    public int maxNumberOfBalloons(String text) {
        int sumB = 0, sumA = 0, sumL = 0, sumO = 0, sumN = 0;
        for (char c : text.toCharArray()) {
            if (c == 'b') {
                sumB++;
            } else if (c == 'a') {
                sumA++;
            } else if (c == 'l') {
                sumL++;
            } else if (c == 'o') {
                sumO++;
            } else if (c == 'n') {
                sumN++;
            }
        }
        sumL /= 2;
        sumO /= 2;
        int rtn = Math.min(sumB, Math.min(sumA, Math.min(sumL, Math.min(sumO, sumN))));
        return rtn;
    }
}
