package com.Benjamin.exam;

/**
 * ClassName:Bilibili20200813_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-13 19:58
 */
public class Bilibili20200813_1 {

    public static int method(int[][] array) {
        int sub1 = array.length;
        int sub2 = array[0].length;

        // 初始化第一行和第一列
        for (int i = 1; i < sub1; i++) {
            array[i][0] += array[i - 1][0];
        }
        for (int j = 1; j < sub2; j++) {
            array[0][j] += array[0][j - 1];
        }

        for (int i = 1; i < sub1; i++) {
            for (int j = 1; j < sub2; j++) {
                int num = array[i][j];
                array[i][j] = Math.min(array[i - 1][j] + num, array[i][j - 1] + num);
            }
        }

        return array[sub1 - 1][sub2 - 1];

    }

    public static void main(String[] args) {
        System.out.println(method(new int[][]{
                new int[]{2, 3, 1, 4},
                new int[]{4, 6, 3, 1},
                new int[]{4, 3, 1, 2},
                new int[]{2, 4, 1, 3}}));
    }

}
