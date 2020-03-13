package com.Benjamin.offer;

/**
 * ClassName:Offer48
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 * 首先看十进制中的加法:
 * 5 + 7 = 12
 * 先进行不进位相加,得到2,然后计算进位,得到10
 * 然后个位和十位相加(也可以考虑递归,本题使用的是循环)
 * 那么二进制中也一样,只不过不进位相加使用异或代替
 * 进位的计算使用的是按位与然后左移一位
 *
 * @author: Benjamin
 * @date: 19-12-29 上午9:01
 */
public class Offer48 {
    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }
}
