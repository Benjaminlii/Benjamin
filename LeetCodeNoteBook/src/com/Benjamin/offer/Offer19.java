package com.Benjamin.offer;

import java.util.ArrayList;

/**
 * ClassName:Offer19
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author: Benjamin
 * @date: 19-11-28 下午2:34
 */
public class Offer19 {
    public static void main(String[] args) {
        int[][] nums = new int[][]{new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9, 10, 11, 12},
                new int[]{13, 14, 15, 16}};
        int[][] nums1 = new int[][]{new int[]{1}};
        System.out.println(new Offer19().printMatrix(nums1));
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>(matrix.length);
        int top = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int down = matrix.length - 1;
        int[] colAdd = new int[]{1, 0, -1, 0};
        int[] rowAdd = new int[]{0, 1, 0, -1};
        int flag = 0;
        int i = 0, j = 0;
        while (!(top > down || left > right)) {
            System.out.println("i = " + i + ", j = " + j);
            System.out.println("matrix[" + i + "][" + j + "] = " + matrix[i][j]);
            ans.add(matrix[i][j]);

            if (i == 0 && j == 0) {
                j++;
                continue;
            }
            if (j == left && i == top && colAdd[flag] == 0 && rowAdd[flag] == -1) {
                // 左上角
                left++;
                flag = (flag + 1) % 4;
            } else if (j == right && i == top && colAdd[flag] == 1 && rowAdd[flag] == 0) {
                // 右上角
                top++;
                flag = (flag + 1) % 4;
            } else if (j == down && i == right && colAdd[flag] == 0 && rowAdd[flag] == 1) {
                // 右下角
                right--;
                flag = (flag + 1) % 4;
            } else if (j == left && i == down && colAdd[flag] == -1 && rowAdd[flag] == 0) {
                // 左下角
                down--;
                flag = (flag + 1) % 4;
            }
            j += colAdd[flag];
            i += rowAdd[flag];
        }
        return ans;
    }
}
