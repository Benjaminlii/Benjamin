package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode62
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * @author: Benjamin
 * @date: 19-11-4 下午12:18
 */
public class LeetCode62 {
    public int uniquePaths(int m, int n) {
        int[][] flag = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    flag[i][j] = 1;
                }else {
                    flag[i][j] = flag[i - 1][j] + flag[i][j - 1];
                }
            }
        }
        return flag[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode62().uniquePaths(7, 3));
    }
}
