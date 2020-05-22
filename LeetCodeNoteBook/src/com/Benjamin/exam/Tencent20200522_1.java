package com.Benjamin.exam;

public class Tencent20200522_1 {


    public static int method(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i]) {
                num = array[i];
            }
        }
        return Math.max(num, array[array.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println(method(new int[]{1, 2, 3, 4, 9, 6, 7, 8}));
    }
}
