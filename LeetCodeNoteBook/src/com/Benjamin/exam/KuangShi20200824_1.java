package com.Benjamin.exam;

import java.util.Arrays;

/**
 * ClassName:KuangShi20200824_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-24 15:38
 */
public class KuangShi20200824_1 {
    public int[] product(int[] nums) {
        // write code here
        int num = 1;
        boolean flag = false;
        for (int i : nums) {
            if (i == 0) {
                flag = true;
                continue;
            }
            num *= i;
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            if (flag) {
                if (nums[i] == 0) {
                    ans[i] = num;
                } else {
                    ans[i] = 0;
                }
            } else {
                ans[i] = num / nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new KuangShi20200824_1().product(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0})));
        System.out.println(Arrays.toString(new KuangShi20200824_1().product(new int[]{})));
    }
}
