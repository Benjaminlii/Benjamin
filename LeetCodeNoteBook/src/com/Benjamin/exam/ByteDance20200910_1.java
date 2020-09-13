package com.Benjamin.exam;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * ClassName:ByteDance20200910_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-13 19:17
 */
public class ByteDance20200910_1 {


    private static int method(int[][] arrays) {
        Set<String> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < arrays.length; i++) {
            int[] array = arrays[i];
            for (int j = 0; j < array.length; j++) {
                String sub = i + "," + j;
                if (array[j] == 1 && !set.contains(sub)) {
//                    method_(set, i, j, arrays, "");
                    method__(set, i, j, arrays);
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void method_(Set<String> set, int i, int j, int[][] arrays, String str) {
        if (!(i >= 0 && i < arrays.length && j >= 0 && j < arrays[0].length)) {
            return;
        }
        if (arrays[i][j] != 1) {
            return;
        }
        // up
        if (!str.equals("down")) {
            method_(set, i - 1, j, arrays, "up");
        }
        // down
        if (!str.equals("up")) {
            method_(set, i + 1, j, arrays, "down");
        }
        // left
        if (!str.equals("right")) {
            method_(set, i, j - 1, arrays, "left");
        }
        // right
        if (!str.equals("left")) {
            method_(set, i, j + 1, arrays, "right");
        }
        set.add(i + "," + j);
    }

    private static void method__(Set<String> set, int i, int j, int[][] arrays) {
        Stack<String> stack = new Stack<>();

        stack.add(i + "," + j);
        while (!stack.empty()) {
            String sub = stack.pop();
            String[] subs = sub.split(",");
            int sub1 = Integer.parseInt(subs[0]);
            int sub2 = Integer.parseInt(subs[1]);
            if (!(sub1 >= 0 && sub1 < arrays.length && sub2 >= 0 && sub2 < arrays[0].length)) {
                continue;
            }
            if (arrays[sub1][sub2] != 1) {
                continue;
            }
            if (set.contains(sub)) {
                continue;
            }

            // up
            stack.add((sub1 - 1) + "," + sub2);
            // down
            stack.add((sub1 + 1) + "," + sub2);
            // left
            stack.add(sub1 + "," + (sub2 - 1));
            // right
            stack.add(sub1 + "," + (sub2 + 1));

            set.add(sub);
        }


    }

    public static void main(String[] args) {
        System.out.println(method(
                new int[][]{
                        {1, 0, 0, 1},
                        {1, 1, 1, 1},
                        {0, 1, 0, 0},
                        {0, 0, 0, 1}
                }));
        System.out.println(method(
                new int[][]{
                        {1, 0, 0, 1},
                        {1, 1, 0, 1},
                        {0, 1, 0, 0},
                        {0, 0, 0, 1}
                }));
    }
}
