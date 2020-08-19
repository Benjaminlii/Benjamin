package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:HuaWei20200819_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-19 19:04
 */
public class HuaWei20200819_1 {
    public String method(int m, int n) {
        StringBuilder sb = new StringBuilder();
        int[][] ans = method_(m, n);
        sb.append("[");
        for (int[] an : ans) {
            sb.append(String.format("[%d,%d],", an[0], an[1]));
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public int[][] method_(int m, int n) {
        List<int[]> ans = new ArrayList<>();
        int count = 1;
        int top = 0;
        int left = 0;
        int right = n - 1;
        int down = m - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                if (isOut(count)) {
                    ans.add(new int[]{top, i});
                }
                count++;
            }
            top++;
            if (top > down) {
                break;
            }

            for (int i = top; i <= down; i++) {
                if (isOut(count)) {
                    ans.add(new int[]{i, right});
                }
                count++;
            }
            right--;
            if (left > right) {
                break;
            }

            for (int i = right; i >= left; i--) {
                if (isOut(count)) {
                    ans.add(new int[]{down, i});
                }
                count++;
            }
            down--;
            if (top > down) {
                break;
            }

            for (int i = down; i >= top; i--) {
                if (isOut(count)) {
                    ans.add(new int[]{i, left});
                }
                count++;
            }
            left++;
            if (left > right) {
                break;
            }
        }

        int[][] ansArray = new int[ans.size()][];
        for (int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(i);
        }
        return ansArray;
    }

    private boolean isOut(int num) {
        int num1 = num % 10;
        num /= 10;
        int num2 = num % 10;
        return num1 == 7 && num2 % 2 != 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m, n = 0;
        try {
            m = in.nextInt();
            n = in.nextInt();
            if (!(m >= 10 && m <= 1000)){
                throw new Exception();
            }
            if (!(n >= 10 && n <= 1000)){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println("[]");
            return;
        }
        System.out.println(new HuaWei20200819_1().method(m, n));
    }
}
