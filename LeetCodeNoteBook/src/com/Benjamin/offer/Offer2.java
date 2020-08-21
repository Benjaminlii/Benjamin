package com.Benjamin.offer;

/**
 * ClassName:Offer2
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 思路:
 * 调函数,或者使用StringBuilder
 *
 * @author: Benjamin
 * @date: 19-11-18 上午9:38
 */
public class Offer2 {
    public String replaceSpace(StringBuilder str) {
        if (str == null) {
            return null;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.deleteCharAt(i);
                str.insert(i, "%20");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Offer2().replaceSpace(new StringBuilder("We Are Happy")));
    }
}
