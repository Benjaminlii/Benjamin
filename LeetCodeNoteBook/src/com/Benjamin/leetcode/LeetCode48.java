package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode48
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 示例 1:
 * 给定 matrix =
 * [
 * ..[1,2,3],
 * ..[4,5,6],
 * ..[7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * ..[7,4,1],
 * ..[8,5,2],
 * ..[9,6,3]
 * ]
 * <p>
 * 思路:
 * nxn的正方形
 * 从外层往内层一层一层移动,每次移动4个对应位子上的格子
 *
 * @author: Benjamin
 * @date: 20-3-18 下午5:40
 */
public class LeetCode48 {
    public void rotate(int[][] matrix) {
        int tmp;
        for (int start = 0, end = matrix.length - 1; start < end; start++, end--) {
            for (int up = start, down = end; up < end; up++, down--) {
                tmp = matrix[start][up];
                matrix[start][up] = matrix[down][start];
                matrix[down][start] = matrix[end][down];
                matrix[end][down] = matrix[up][end];
                matrix[up][end] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new LeetCode48().rotate(array);
        System.out.println(Arrays.deepToString(array));
    }
}
