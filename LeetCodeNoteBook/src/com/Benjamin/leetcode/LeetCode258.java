package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode258
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * <p>
 * 思路:
 * 使用循环和递归当然很简单,但这道题的进阶是不适用循环和递归,在O(1)的时间内完成.
 * 通过对例子 addDigits(38)=2 中参数38的拆解,过程如下:
 * 38 = 3*10+8 = 3*9+(3+8) = 3*9+11 = 3*9+1*10+1 = 3*9+1*9+(1+1) = 4*9+2
 * 这样一来,就等同于:讲参数中的9的倍数去掉,也就是对9取余
 * addDigits(x) = x%9
 * 但是还有一个问题,9的整数倍得到的结果为0,而实际上应该为9
 * 所以进行一下处理
 * addDigits(x) = (x-1)%9+1
 *
 * @author: Benjamin
 * @date: 19-9-4 下午4:25
 */
public class LeetCode258 {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
