package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer51
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * <p>
 * 思路:
 * 将B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]
 * 分解为B[i]=(A[0]*A[1]*...*A[i-1]) * (A[i+1]*...*A[n-1])
 * 使用动态规划,先正序循环一遍数组计算每一个B[i]的前一部分,然后倒序计算后半部分
 *
 * @author: Benjamin
 * @date: 20-1-2 下午8:59
 */
public class Offer51 {
    public int[] multiply(int[] A) {
        int[] b = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            b[i] = i != 0 ? A[i - 1] * b[i - 1] : 1;
        }
        int temp = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            temp *= A[i + 1];
            b[i] *= temp;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer51().multiply(new int[]{1, 2, 3, 4, 5})));
    }
}
