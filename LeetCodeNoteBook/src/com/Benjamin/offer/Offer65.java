package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer65
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如
 * a b c e
 * s f c s
 * a d e e
 * 矩阵中
 * 包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * @author: Benjamin
 * @date: 20-1-16 下午4:39
 */
public class Offer65 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        char[][] chars = new char[rows][cols];
        int row = 0,col = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (col >= cols){
                row++;
                col %= cols;
            }
            chars[row][col] = matrix[i];
            col++;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Offer65().hasPath("abcesfcsadee".toCharArray(),
                3, 4, "bcced".toCharArray()));
    }
}
