package com.Benjamin.offer;

/**
 * ClassName:Offer49
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 把字符串转换成整数
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 *
 * @author: Benjamin
 * @date: 19-12-30 下午4:48
 */
public class Offer49 {
    public int StrToInt(String str) {
        Integer res = 0;
        try {
            res = new Integer(str);
        } catch (NumberFormatException e) {

        } finally {
            return res;
        }
    }

    public int StrToInt_(String str) {
        if (str == null || str.length() == 0){
            return 0;
        }
        int sum = 0;
        char[] chars = str.toCharArray();
        boolean flag = true;
        boolean a = false;
        if (chars[0] == '+') {
            flag = true;
            a = true;
        } else if (chars[0] == '-') {
            flag = false;
            a = true;
        }
        int i = a ? 1 : 0;
        for (; i < chars.length; i++) {
            char c = chars[i];
            if (c > '9' || c < '0') {
                return 0;
            } else {
                sum = sum * 10 + c - '0';
            }
        }
        return flag ? sum : -sum;

    }

    public static void main(String[] args) {
        System.out.println(new Offer49().StrToInt("-11231212323123"));
    }
}
