package com.Benjamin.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName:Ali20200320_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-20 上午9:17
 */
public class Ali20200320_1 {

    private static int maxLength = 0;

    private static int method(String[] strs) {
        int[] dp = new int[maxLength];
        for (int i = 0; i < strs.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < strs.length - 1 - i; j++) {
                if (strs[j].charAt(0) > strs[j + 1].charAt(0)) {
                    String tmp = strs[j];
                    strs[j] = strs[j + 1];
                    strs[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }

        for (String str : strs) {
            int sub = strs.length;
            for (int i = 0; i < maxLength; i++) {
                if (dp[i]!=0&&str.charAt(0)>dp[0]){
                    sub = Math.max(sub,i+str.length());
                }
                System.out.println(sub);
                dp[sub] = str.charAt(str.length()-1);
            }
        }
        for (int i = dp.length-1; i >0; i--) {
            if (dp[i]!=0){
                return i;
            }
        }
        System.out.println(Arrays.toString(strs));
        System.out.println(Arrays.toString(dp));

        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();

        String[] strs = new String[num];
        for (int i = 0; i < num; i++) {
            strs[i] = in.nextLine();
            maxLength += strs[i].length();
        }

        System.out.println(method(strs));
        in.close();

    }

}
