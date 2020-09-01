package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:PDD20200901_4
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-01 20:28
 */
public class PDD20200901_4 {

    private static int method(int num, int[] array) {
        int[] nums = new int[num];
        for (int i = 0; i < num; i++) {
            nums[i] = i + 1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            for (int j = 0; j < array.length; j++) {
                // 整除
                if (nums[i] % array[j] == 0) {
                    nums[i] = 0;
                    int sub = i + array[j];
                    while (sub < nums.length) {
                        nums[sub] = 0;
                        sub += array[j];
                    }
                    break;
                }
            }
        }

        int ans = 0;
        for (int i : nums) {
            ans += i == 0 ? 1 : 0;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] array = new int[in.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        System.out.println(method(num, array));

    }
}
