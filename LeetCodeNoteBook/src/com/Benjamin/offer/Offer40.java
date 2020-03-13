package com.Benjamin.offer;

/**
 * ClassName:Offer40
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * <p>
 * 根据之前做过的异或的那个题
 * 先异或一遍,得出的结果是这两个数字异或的结果
 * 从这个结果的二进制表示中找到最低位的1
 * 因为这个是一个两个数异或的结果,所以这个1的位置对应的原先的两个数的该位置上肯定是一个0,一个1
 * 并且其他所有的数在这个位置上肯定是0或者偶数个1
 * 那么按照这个位置上是0还是1可以将数组分为两个数组,分别进行异或就可以得到两个数
 *
 * @author: Benjamin
 * @date: 19-12-13 下午8:35
 */
public class Offer40 {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int num = 0;
        for (int i : array) {
            num ^= i;
        }
        int bt = 0;
        while ((num & 1) == 0) {
            bt += 1;
            num >>= 1;
        }
        for (int i : array) {
            if (((i >> bt)&1) == 1) {
                num1[0] ^= i;
            } else {
                num2[0] ^= i;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,4,3,6,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        new Offer40().FindNumsAppearOnce(array, num1, num2);
        System.out.println(num1[0] + ", " + num2[0]);
    }
}
