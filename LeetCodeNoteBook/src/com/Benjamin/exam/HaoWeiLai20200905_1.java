package com.Benjamin.exam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName:HaoWeiLai20200905_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-05 13:49
 */
public class HaoWeiLai20200905_1 {
    private static Set<String> set = new HashSet<>();

    private static boolean method(boolean[][] array, int startSub1, int startSub2, String way) {

        startSub1 = Math.abs(startSub1 % array.length);
        startSub2 = Math.abs(startSub2 % array[0].length);

        if (!array[startSub1][startSub2]) {
            return false;
        }

        // this
        String str = startSub1 + "," + startSub2;
//        System.out.println(str);
//        System.out.println(set);
//        System.out.println();
        if (set.contains(str)) {
            return true;
        }
        set.add(str);
        // up
        if (!"down".equals(way) && method(array, startSub1 - 1, startSub2, "up")) {
            return true;
        }
        // down
        if (!"up".equals(way) && method(array, startSub1 + 1, startSub2, "down")) {
            return true;
        }
        // left
        if (!"right".equals(way) && method(array, startSub1, startSub2 - 1, "left")) {
            return true;
        }
        // right
        if (!"left".equals(way) && method(array, startSub1, startSub2 + 1, "right")) {
            return true;
        }

        set.remove(str);

        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        boolean[][] array = new boolean[n][m];
        int startSub1 = 0;
        int startSub2 = 0;
        for (int i = 0; i < n; i++) {
            String[] strs = in.nextLine().split("");
            for (int j = 0; j < m; j++) {
                if ("S".equals(strs[j])) {
                    startSub1 = i;
                    startSub2 = j;
                }
                array[i][j] = !"#".equals(strs[j]);
            }
        }

        System.out.println(method(array, startSub1, startSub2, "") ? "Yes" : "No");

    }
}
/*
5 4
##.#
##S#
#..#
#.##
#..#

5 4
####
##S#
#..#
#.##
#..#

*/
