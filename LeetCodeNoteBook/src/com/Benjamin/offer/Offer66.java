package com.Benjamin.offer;

/**
 * ClassName:Offer66
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 机器人的运动范围
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 * 请问该机器人能够达到多少个格子？
 *
 * 类似走迷宫,只不过标记走过的路径,不再标记回去
 * 最后统计数组中标记的元素就能得到能到达的格子数
 *
 * @author: Benjamin
 * @date: 20-1-17 上午11:26
 */
public class Offer66 {
    private static boolean[] flag = null;

    public int movingCount(int threshold, int rows, int cols) {
        flag = new boolean[rows * cols];
        moving(threshold, rows, cols, 0, 0);
        int count = 0;
        for (boolean b : flag) {
            count = b ? count + 1 : count;
        }
        return count;
    }

    public void moving(int threshold, int rows, int cols, int row, int col) {
        if (flag[row * cols + col] || !allow(threshold, row, col)) {
            return;
        }
        flag[row * cols + col] = true;
        if (row > 0) {
            moving(threshold, rows, cols, row - 1, col);
        }
        if (row < rows - 1) {
            moving(threshold, rows, cols, row + 1, col);
        }
        if (col > 0) {
            moving(threshold, rows, cols, row, col - 1);
        }
        if (col < cols - 1) {
            moving(threshold, rows, cols, row, col + 1);
        }
    }

    public boolean allow(int threshold, int row, int col) {
        int count = 0;
        int num;
        while (row != 0 || col != 0) {
            if (row != 0) {
                num = row % 10;
                row /= 10;
                count += num;
            }
            if (col != 0) {
                num = col % 10;
                col /= 10;
                count += num;
            }
        }
        return count <= threshold;
    }

    public static void main(String[] args) {
        Offer66 offer66 = new Offer66();
        System.out.println(offer66.movingCount(10, 1, 10));
    }
}
