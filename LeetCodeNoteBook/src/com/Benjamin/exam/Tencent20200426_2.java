package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:Tencent20200426_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-4-26 下午8:29
 */
public class Tencent20200426_2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int num1 = in.nextInt();
        in.nextLine();
        for (int i = 0; i < num1; i++) {
            List<int[]> lista = new ArrayList<>();
            List<int[]> listb = new ArrayList<>();

            int num2 = in.nextInt();
            in.nextLine();
            for (int j = 0; j < num2; j++) {
                int ax = in.nextInt();
                int ay = in.nextInt();
                lista.add(new int[]{ax, ay});
            }
            for (int j = 0; j < num2; j++) {
                int bx = in.nextInt();
                int by = in.nextInt();
                listb.add(new int[]{bx, by});
            }

            double ans = Double.MAX_VALUE;
            for (int[] inta : lista) {
                for (int[] intb : listb) {
                    double number = Math.sqrt(Math.pow(inta[0] - intb[0], 2) + Math.pow(inta[1] - intb[1], 2));
                    ans = ans > number ? number : ans;
                }
            }
            System.out.printf("%.3f\n",ans);

        }

    }
}
