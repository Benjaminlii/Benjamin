package com.Benjamin.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<Integer> nums = new ArrayList<>();
//        nums.add(1);
//        nums.add(4);
//        nums.add(3);
//        nums.add(2);
//        nums.add(5);
        nums.add(1);
        nums.add(2);
        nums.add(2);
        nums.add(1);
        nums.add(1);
        nums.add(1);

        System.out.println(demo(nums));
    }

    public static int demo(List<Integer> nums) {
        int count = 0;
        int i = 0, j = nums.size() - 1;

        while (i < j) {
            while (nums.get(i + 1) > nums.get(i)) {
                i++;
            }
            while (nums.get(j) < nums.get(j - 1)) {
                j--;
            }

            if (i == j) {
                break;
            }

            int num1 = nums.get(i) + 1 - nums.get(i + 1);
            int num2 = nums.get(j) + 1 - nums.get(j - 1);
            if (num1 < num2) {
                nums.set(i + 1, nums.get(i) + 1);
                count += num1;
                i++;
            } else {
                nums.set(j - 1, nums.get(j) + 1);
                count += num2;
                j--;
            }
        }

        return count;
    }
}
