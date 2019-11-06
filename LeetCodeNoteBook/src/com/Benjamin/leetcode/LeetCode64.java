package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode64
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * @author: Benjamin
 * @date: 19-11-6 上午10:04
 */
public class LeetCode64 {
    public int minPathSum(int[][] grid) {
        int row = 0, col = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                row = i > 0 ? grid[i - 1][j] : Integer.MAX_VALUE;
                col = j > 0 ? grid[i][j - 1] : Integer.MAX_VALUE;
                grid[i][j] += row > col ? col : row;
            }
        }
//        System.out.println(Arrays.deepToString(grid));
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{new int[]{1, 3, 1},
                new int[]{1, 5, 1},
                new int[]{4, 2, 1}};
        System.out.println(new LeetCode64().minPathSum(nums));
    }
}
