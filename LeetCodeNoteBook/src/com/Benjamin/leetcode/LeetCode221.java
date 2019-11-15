package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode221
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * <p>
 * dp[i][j]代表与i,j为游侠叫的最大正方形边长
 * dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
 *
 * @author: Benjamin
 * @date: 19-11-15 下午2:21
 */
public class LeetCode221 {
    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    int l = j - 1 >= 0 ? matrix[i][j - 1] - '0' : 0;
                    int lu = i - 1 >= 0 && j - 1 >= 0 ? matrix[i - 1][j - 1] - '0' : 0;
                    int u = i - 1 >= 0 ? matrix[i - 1][j] - '0' : 0;
                    matrix[i][j] = (char) (Math.min(Math.min(l, u),lu)+1+'0');
                    ans = Math.max(ans, matrix[i][j] - '0');
                }
            }
        }
        return (int) Math.pow(ans, 2);
    }

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                new char[]{'1', '1', '1', '1', '1'},
                new char[]{'0', '1', '0', '1', '1'},
                new char[]{'0', '1', '1', '0', '1'},
                new char[]{'1', '1', '0', '1', '1'}
        };
        System.out.println(new LeetCode221().maximalSquare(chars));
    }
}
