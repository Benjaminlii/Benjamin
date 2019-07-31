package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 * 返回这两个区间列表的交集。
 *
 * author:Benjamin
 * date:2019.7.31
 */
public class LeetCode986 {

    public static void main(String[] args) {
        int A[][] = new int[][]{new int[]{0, 2}, new int[]{5, 10}, new int[]{13, 23}, new int[]{24, 25}};
        int B[][] = new int[][]{new int[]{1, 5}, new int[]{8, 12}, new int[]{15, 24}, new int[]{25, 26}};
        int c[][] = new LeetCode986().intervalIntersection(A, B);
        for (int[] ints : c) {
            System.out.println("[" + ints[0] + "," + ints[1] + "]");
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        //交集
        List<List<Integer>> intersection = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            //A集合每段的起始和结束
            int Astart = A[i][0];
            int Aend = A[i][1];
            for (int j = 0; j < B.length; j++) {
                //B集合每段的起始和结束
                int Bstart = B[j][0];
                int Bend = B[j][1];
                //判断是否有交集部分
                if(Bend < Astart){
                    //B在A之前
                    continue;
                }
                if(Bstart > Aend){
                    //B在A之后
                    break;
                }
                //保存交集
                List<Integer> interval = new ArrayList<>();
                interval.add(0, Math.max(Astart, Bstart));
                interval.add(1, Math.min(Aend, Bend));
                intersection.add(interval);
            }
        }

        //转化格式
        int rtn[][] = new int[intersection.size()][2];
        int i = 0;
        for (List<Integer> list : intersection) {
            rtn[i][0] = list.get(0);
            rtn[i][1] = list.get(1);
            i++;
        }
        return rtn;
    }
}
