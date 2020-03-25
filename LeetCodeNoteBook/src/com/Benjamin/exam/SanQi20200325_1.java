package com.Benjamin.exam;

/**
 * ClassName:SanQi20200325_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-25 下午8:18
 */
public class SanQi20200325_1 {

    // 这个是加了一圈0之后的数组
    private static int[][] array = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };

    public static int method(int[][] array) {
        int ans = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    ans += getNumber(array, i, j);
                }
            }
        }
        return ans;
    }

    public static int getNumber(int[][] array, int i, int j) {
        int ans = 0;
        ans += array[i - 1][j] == 1 ? 0 : 1; // 上
        ans += array[i][j + 1] == 1 ? 0 : 1; // 右
        ans += array[i + 1][j] == 1 ? 0 : 1; // 下
        ans += array[i][j - 1] == 1 ? 0 : 1; // 左
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(method(array));
    }


}
