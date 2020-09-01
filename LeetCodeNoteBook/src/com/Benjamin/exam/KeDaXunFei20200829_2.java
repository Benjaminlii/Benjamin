package com.Benjamin.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName:KeDaXunFei20200829_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-29 19:13
 */
public class KeDaXunFei20200829_2 {
    private static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minSub = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j]<array[minSub]){
                    minSub = j;
                }
            }
            int tmp = array[i];
            array[i] = array[minSub];
            array[minSub] = tmp;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String s = in.nextLine();
        String[] ss = s.split(",");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(ss[i]);
        }
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
