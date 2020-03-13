package com.Benjamin.offer;

/**
 * ClassName:Offer7
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 * 思路:
 * 递归,动态规划都可以
 *
 * @author: Benjamin
 * @date: 19-11-22 上午11:35
 */
public class Offer7 {
    public int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return Fibonacci(n - 2) + Fibonacci(n - 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(new Offer7().Fibonacci(i));
        }
    }
}
