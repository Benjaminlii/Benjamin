package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:Didi20200821_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-21 20:06
 */
public class Didi20200821_2 {
    public static int method(int num, List<int[]> ans) {
        int count = 0;
        for (int a = 1; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                for (int c = 0; c < 10; c++) {
                    if (a == b || b == c || a == c) {
                        continue;
                    }
                    int abc = a * 100 + b * 10 + c;
                    int acc = a * 100 + c * 10 + c;
                    if (abc + acc == num) {
                        ans.add(new int[]{abc, acc});
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.close();

        List<int[]> ans = new ArrayList<>();
        System.out.println(method(num, ans));
        for (int[] an : ans) {
            System.out.printf("%d %d\n", an[0], an[1]);
        }
    }
}
