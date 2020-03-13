package com.Benjamin.offer;

/**
 * ClassName:Offer43
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 左旋转字符串
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 * 是不是很简单？
 * OK，搞定它！
 *
 * 思路:
 * 使用StringBuffer,循环append
 *
 * @author: Benjamin
 * @date: 19-12-26 下午1:59
 */
public class Offer43 {
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        int sub = n%length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(str.charAt(sub));
            sub++;
            sub%=length;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Offer43().LeftRotateString("abcdefg", 100));
    }
}
