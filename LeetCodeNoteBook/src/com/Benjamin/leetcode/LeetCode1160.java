package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode1160
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * 示例 1：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 *
 * @author: Benjamin
 * @date: 19-10-18 上午8:47
 */
public class LeetCode1160 {

    public static void main(String[] args) {
        String[] strings = new String[]{"hello","world","leetcode"};
        String chars = "welldonehoneyr";
        System.out.println(new LeetCode1160().countCharacters(strings, chars));
    }

    public int countCharacters(String[] words, String chars) {
        char[] charArray = chars.toCharArray();
        boolean[] flag = new boolean[charArray.length];
        int ans = 0;

        for (String word : words) {
            for (int i = 0; i < flag.length; i++) {
                flag[i] = false;
            }

            boolean mark = true;
            for (char c : word.toCharArray()) {
                for (int i = 0; i < charArray.length; i++) {
//                    System.out.println("c = " + c + ", charArray[" + i + "] = " + charArray[i] + ", !flag[" + i + "] = " + !flag[i]);
                    if (c == charArray[i] && !flag[i]){
                        flag[i] = true;
                        break;
                    }else if(i == charArray.length-1){
                        mark = false;
                    }
                }
                if(!mark){
                    break;
                }
            }
            if (mark){
                ans += word.length();
            }
        }
        return ans;
    }
}
