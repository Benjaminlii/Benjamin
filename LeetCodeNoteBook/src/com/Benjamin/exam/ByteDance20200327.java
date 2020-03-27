package com.Benjamin.exam;

/**
 * ClassName:ByteDance20200327
 * Package:com.Benjamin
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-27 下午3:36
 */
public class ByteDance20200327 {

    public static int method(int[] array) {
        int[] min = new int[array.length];
        int[] max = new int[array.length];

        int minVal = array[array.length - 1];
        for (int i = array.length - 1; i >= 0; i--) {
            min[i] = minVal;
            minVal = array[i] > minVal ? minVal : array[i];
        }

        int maxVal = array[0];
        int ans = -1;
        for (int i = 0; i < array.length; i++) {
            max[i] = maxVal;
            maxVal = array[i] < maxVal ? maxVal : array[i];
            if (array[i] < min[i] && array[i] > max[i]) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(method(new int[]{4, 5, 6, 8, 7}));
        System.out.println(method(new int[]{4, 1, 1, 4, 3, 1, 5, 9, 34, 45, 25, 6, 8, 7}));
    }

}
