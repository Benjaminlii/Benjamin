package com.Benjamin.offer;

/**
 * ClassName:Offer34
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 *
 * 思路:
 * 使用map存储每个字符出现的数量
 * 使用数组存储(hash,char->int当做下标)来改进
 *
 * @author: Benjamin
 * @date: 19-12-5 下午12:35
 */
public class Offer34 {
    public int FirstNotRepeatingChar(String str) {
        if (str == null){
            return -1;
        }
        int[] counts = new int['z'-'A'+1];
        for (char c : str.toCharArray()) {
            counts[c-'A']++;
        }
        for (int i = 0; i < str.toCharArray().length; i++) {
            if (counts[str.charAt(i)-'A'] == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Offer34().FirstNotRepeatingChar("NXWtnzyoHoBhUJaPauJaAitLWNMlkKwDYbbigdMMaYfkVPhGZcrEwp"));
    }
}
