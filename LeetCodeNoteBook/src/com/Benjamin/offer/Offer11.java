package com.Benjamin.offer;

/**
 * ClassName:Offer11
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二进制中1的个数
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * @author: Benjamin
 * @date: 19-11-24 上午9:58
 */
public class Offer11 {
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 0b1;
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Offer11().NumberOf1(-7));
    }
}
