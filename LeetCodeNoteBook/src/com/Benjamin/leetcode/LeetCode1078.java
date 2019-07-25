package com.Benjamin.leetcode;

import java.util.ArrayList;

/**
 * 给出第一个词first和第二个词second，考虑在某些文本text中可能以"first second third"形式出现的情况
 * 其中second紧随first出现，third紧随second出现。
 * <p>
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 * <p>
 * input:text = "alice is a good girl she is a good student", first = "a", second = "good"
 * <p>
 * author:Benjamin
 * date:2019.7.23
 */

public class LeetCode1078 {
    public static void main(String[] args) {
        System.out.println(new LeetCode1078().findOcurrences(
                "alice is a good girl she is a good student",
                "a",
                "good"
        ));
    }

    public String[] findOcurrences(String text, String first, String second) {
        ArrayList<String> rtn = new ArrayList<>();
        //将待查找子串按空格分割为单词
        String[] words = text.split(" ");

        for (int i = 0; i < words.length - 2; i++) {
            if (first.equals(words[i]) && second.equals(words[i + 1]))
                rtn.add(words[i + 2]);
        }

        String[] rtn1 = new String[rtn.size()];
        for(int i = 0; i < rtn.size(); i++){
            rtn1[i] = rtn.get(i);
        }

        return rtn1;
    }
}
