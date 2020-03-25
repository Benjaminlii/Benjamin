package com.Benjamin.exam;

/**
 * ClassName:SanQi20200325_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-25 下午8:21
 */
public class SanQi20200325_2 {

    public static int method(int[] array) {
        int min = array[0];
        int ans = Integer.MIN_VALUE;


        for (int i = 1; i < array.length; i++) {
            ans = Math.max(ans, array[i] - min);
            min = Math.min(min, array[i]);
        }

        return ans > 0 ? ans : 0;

    }

    public static void main(String[] args) {
        System.out.println(method(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(method(new int[]{7, 6, 4, 3, 1}));
    }
}
