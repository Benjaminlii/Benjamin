package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer50
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 * @author: Benjamin
 * @date: 19-12-30 下午5:46
 */
public class Offer50 {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[Math.abs(numbers[i])] < 0) {
                duplication[0] = Math.abs(numbers[i]);
                return true;
            }
            numbers[Math.abs(numbers[i])] *= -1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = new int[1];
        System.out.println(new Offer50().duplicate(new int[]{2, 0, 3, 1, 4}, 5, array) + " + " + Arrays.toString(array));
        System.out.println(new Offer50().duplicate(new int[]{2, 0, 3, 1, 4, 3, 1}, 5, array) + " + " + Arrays.toString(array));
    }
}
