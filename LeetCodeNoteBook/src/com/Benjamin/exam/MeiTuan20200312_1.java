package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:MeiTuan20200312_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 有一个2*n的网格，有一个人位于(1,1)的位置，即左上角，他希望从左上角走到右下角，即(2,n)的位置。在每一次，他可以进行三种操作中的一种：
 * 1． 向右走一格，即从(x,y)到(x,y+1);
 * 2． 向上右方走一格，即，如果他在(2,y)的位置可以走到(1,y+1);
 * 3． 向下右方走一格，即，如果他在(1,y)的位置可以走到(2,y+1);
 * 问题当然不会这么简单，在这2*n的格子中，有一部分格子上有障碍物，他不能停在障碍物上，当然也不能走出网格，请问他有多少种不同的路线可以到达(2,n)。
 * 输入
 * 输入第一行仅包含一个正整数n，表示网格的长度。(1<=n<=50)
 * 接下来有2行,每行有n个字符，“X”代表障碍物，“.”代表可以停留。
 * 输出
 * 如果没有可以到达的路线则输出-1，否则输出方案数量。
 *
 * @author: Benjamin
 * @date: 20-3-12 下午7:06
 */
public class MeiTuan20200312_1 {
    private static Scanner in = new Scanner(System.in);
    private static int[][] dp;
    private static int length;

    public static int method(char[][] array, int row, int col) {
        if (length == 1||array[1][length-1] != '.') {
            return -1;
        }
        dp[1][length - 1] = 1;
        dp[0][length - 1] = 0;
        for (int i = length - 2; i >= 0; i--) {
            dp[0][i] = array[0][i] == '.' ? (dp[0][i + 1] + dp[1][i + 1]) : 0;
            dp[1][i] = array[1][i] == '.' ? (dp[1][i + 1] + dp[0][i + 1]) : 0;
        }
        return dp[0][0] != 0 ? dp[0][0] : -1;
    }

    public static void main(String[] args) {
        // 输入
        length = in.nextInt();
        dp = new int[2][length];
        in.nextLine();
        char[][] array = new char[2][];
        array[0] = in.nextLine().toCharArray();
        array[1] = in.nextLine().toCharArray();
        System.out.println(method(array, 0, 0));
    }
}
