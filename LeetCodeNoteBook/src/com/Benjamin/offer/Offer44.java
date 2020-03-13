package com.Benjamin.offer;

/**
 * ClassName:Offer44
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 翻转单词顺序列
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。
 * 后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 * <p>
 * 思路:
 * 按照空格分割为字符串数组
 * 倒序拼接,中间插入空格
 *
 * @author: Benjamin
 * @date: 19-12-26 下午2:18
 */
public class Offer44 {
    public String ReverseSentence(String str) {
        if (str == null || str.trim().equals("")) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String[] strs = str.split(" ");
        for (int i = strs.length - 1; i >= 0; i--) {
            stringBuffer.append(strs[i]).append(" ");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Offer44().ReverseSentence(""));
    }
}
