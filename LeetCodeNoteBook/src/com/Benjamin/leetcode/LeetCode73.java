package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Benjamin
 * date:2019.8.1
 */
public class LeetCode73 {

    public static void main(String[] args) {
        int matrix[][] = new int[][]{
                new int[]{0,9,3,3,8,2,1,4,1,7,1,2,7},
                new int[]{6,0,2,3,3,8,5,1,9,3,2,0,7},
                new int[]{8,4,6,0,2,6,1,5,1,0,7,2,6},
                new int[]{1,1,9,3,9,6,5,1,1,1,1,7,2},
                new int[]{0,0,6,3,9,4,7,5,6,0,3,7,7},
                new int[]{5,9,7,9,6,8,1,5,3,0,3,8,3},
                new int[]{5,1,7,4,3,9,4,9,2,6,5,0,3}
        };

        printArray2(matrix);
        new LeetCode73().setZeroes(matrix);
        printArray2(matrix);
    }

    public static void printArray2(int[][] matrix){
        System.out.println("[");
        for (int[] ints : matrix) {
            System.out.print("    [");
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public void setZeroes(int[][] matrix) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(i);
                    integers.add(j);
                    lists.add(integers);
                }
            }
        }
        for (List<Integer> list : lists) {
            int i = list.get(0);
            int j = list.get(1);
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][j] = 0;
            }
            for (int k = 0; k < matrix[0].length; k++) {
                matrix[i][k] = 0;
            }
        }
    }
}
