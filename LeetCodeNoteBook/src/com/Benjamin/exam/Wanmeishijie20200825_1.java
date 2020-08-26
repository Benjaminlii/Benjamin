package com.Benjamin.exam;


import java.util.Scanner;

/**
 * ClassName:Wanmeishijie20200317_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-17 下午7:41
 */
public class Wanmeishijie20200825_1 {

    private static int method(int knapsackCapacity, int[] volumes, int[] values) {
        int size = values.length;
        int[][] dp = new int[size][knapsackCapacity + 1];
        for (int i = 0; i <= knapsackCapacity; i++) {
            dp[0][i] = volumes[0] <= i ? values[0] : 0;
        }
        for (int i = 1; i < size; i++) {
            for (int j = 0; j <= knapsackCapacity; j++) {
                dp[i][j] = dp[i - 1][j];
                if (volumes[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], values[i] + dp[i - 1][j - volumes[i]]);
                }
            }
        }

        return dp[size - 1][knapsackCapacity];
    }

    public static void main(String[] args) {
        System.out.println(method(15, new int[]{5, 3, 4, 6}, new int[]{20, 10, 12, 30}));
    }

    public static void main_(String[] args) {
        Scanner input = new Scanner(System.in);
        int knapsackCapacity = Integer.parseInt(input.nextLine().trim());

        String[] volumesStr = input.nextLine().split(" ");
        int[] volumes = new int[volumesStr.length];
        for (int i = 0; i < volumesStr.length; i++) {
            volumes[i] = Integer.parseInt(volumesStr[i].trim());
        }

        String[] valuesStr = input.nextLine().split(" ");
        int[] values = new int[valuesStr.length];
        for (int i = 0; i < valuesStr.length; i++) {
            values[i] = Integer.parseInt(valuesStr[i].trim());
        }

        if (volumes.length == values.length) {
            System.out.println(method(knapsackCapacity, volumes, values));
        } else {
            System.out.println("道具数量不一致。");
        }
        input.close();
    }
}
