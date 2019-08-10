package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName:Test
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-8-8 下午5:33
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(getMaxNum("TTTYYYTTTTTTTTTTTTTTTTTTTTTTTTTYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY"));
        System.out.println(getNumBetweenAAndB(-23, -23));
    }

    public static int getMaxNum(String ty) {
        int rtn = 0;
        List<String> tyList = new ArrayList<>(Arrays.asList(ty.split("")));
        int length = tyList.size();
        int flag[][] = new int[length][2];
        for (int[] ints : flag) {
            ints[0] = 0;
            ints[1] = 0;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if ("T".equals(tyList.get(i))) {
                    flag[j][1]++;
                } else if ("Y".equals(tyList.get(i))) {
                    flag[j][0]++;
                }
                if (flag[j][0] == flag[j][1]) {
                    int num = flag[j][0] + flag[j][1];
                    if (num > rtn) {
                        rtn = num;
                    }
                }
            }
        }
        return rtn;
    }

    public static String getNumBetweenAAndB(int a, int b) {
        int rtn = 0;

        String lastNumA = (a + "").charAt((a + "").length() - 1) + "";

        String lastNumB = (b + "").charAt((b + "").length() - 1) + "";

        if ("0".equals(lastNumA) || "4".equals(lastNumA) ||
                "0".equals(lastNumB) || "4".equals(lastNumB)) {
            return "illegal input";
        }
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = a + 1; i <= b; i++) {
            String numString = i + "";
            String lastNum = numString.charAt(numString.length() - 1) + "";
            if ("0".equals(lastNum) || "4".equals(lastNum)) {
                continue;
            }
            rtn++;
        }

        return rtn + "";
    }


}
