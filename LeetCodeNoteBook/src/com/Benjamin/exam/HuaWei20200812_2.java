package com.Benjamin.exam;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:HuaWei20200812_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-12 19:22
 */
public class HuaWei20200812_2 {
    private static class Step {
        int row;
        int col;

        public Step(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean method(int[][] array, int num) {
        boolean[][] dp = new boolean[array.length][array[0].length];
        List<Step> queue = new LinkedList<>();

        queue.add(new Step(0, 0));
        dp[0][0] = true;

        while (!queue.isEmpty()) {
            Step thisStep = queue.get(queue.size()-1);
            queue.remove(0);
            if (thisStep.row == array.length-1 && thisStep.col == array[0].length-1) {
                return true;
            }
            Step step1 = getNextStep(thisStep, num, 0, 1, array);
            if (step1 != null && !dp[step1.row][step1.col]) {
                dp[step1.row][step1.col] = true;
                queue.add(step1);
            }
            Step step2 = getNextStep(thisStep, num, 1, 0, array);
            if (step2 != null && !dp[step2.row][step2.col]) {
                dp[step2.row][step2.col] = true;
                queue.add(step2);
            }
            Step step3 = getNextStep(thisStep, num, 0, -1, array);
            if (step3 != null && !dp[step3.row][step3.col]) {
                dp[step3.row][step3.col] = true;
                queue.add(step3);
            }
            Step step4 = getNextStep(thisStep, num, -1, 0, array);
            if (step4 != null && !dp[step4.row][step4.col]) {
                dp[step4.row][step4.col] = true;
                queue.add(step4);
            }
        }
        return false;
    }

    private static Step getNextStep(final Step thisStep, final int strides, int row, int col, int[][] array) {
        Step nextStep = new Step(thisStep.row + strides * row, thisStep.col + strides * col);
        if (nextStep.row > array.length || nextStep.col > array[0].length || nextStep.row < 0 || nextStep.col < 0) {
            return null;
        }
        if (array[nextStep.row][nextStep.col] == 0) {
            return null;
        }
        return nextStep;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{
                new int[]{1, 0, 1, 0, 0},
                new int[]{0, 1, 1, 0, 1},
                new int[]{0, 0, 1, 0, 1}
        };
        System.out.println(method(array, 2));
    }
}
