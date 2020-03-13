package com.Benjamin.offer;

/**
 * ClassName:Offer12
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 *
 * 记的判断幂次的正负
 *
 * @author: Benjamin
 * @date: 19-11-24 上午10:13
 */
public class Offer12 {

    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        float count = 1;
        boolean isPos = exponent > 0;
        exponent = Math.abs(exponent);
        for (int i = 0; i < exponent; i++) {
            count *= base;
        }
        if (isPos) {
            return count;
        }else {
            return 1/count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Offer12().Power(5,-2));
    }
}
