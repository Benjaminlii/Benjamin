package com.Benjamin.exam;

import java.util.List;
import java.util.Scanner;

/**
 * ClassName:Tencent20200823_4
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 20:47
 */
public class Tencent20200823_4 {

    private static int method(int[] array, int min, int length) {
        int nextMin = Integer.MAX_VALUE;
        int nextLength = length;
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 && !flag) {
                count++;
                flag = true;
            } else if (array[i] <= 0) {
                flag = false;
            }
            array[i] -= min;
            if (array[i] > 0) {
                nextMin = Math.min(nextMin, array[i]);
            } else if (array[i] == 0) {
                nextLength--;
            }
        }
        if (nextLength == 0) {
            return Math.min(length, min * count);
        }
        return Math.min(length, min * count + method(array, nextMin, nextLength));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
            min = Math.min(min, array[i]);
        }
        System.out.println(method(array, min, array.length));
    }
}
