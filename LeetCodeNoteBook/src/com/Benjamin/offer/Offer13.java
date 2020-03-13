package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer13
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * <p>
 * 思路:
 * 类比插入排序的移动
 *
 * @author: Benjamin
 * @date: 19-11-25 下午12:04
 */
public class Offer13 {
    public void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                continue;
            }
            for (int j = i; j > 0; j--) {
                if (array[j - 1] % 2 == 0) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        new Offer13().reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }
}
