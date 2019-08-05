package com.Benjamin.leetcode;


/**
 * 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 * <p>
 * author:Benjamin
 * date:2019.8.5
 */
public class LeetCode165 {

    public static void main(String[] args) {
        System.out.println(new LeetCode165().compareVersion("7.5.2.4", ""));
    }

    public int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        int length = Math.max(array1.length, array2.length);

        for (int i = 0; i < length; i++) {
            int num1, num2;
            if (i >= array1.length) {
                num1 = 0;
                num2 = Integer.valueOf(array2[i]);
            } else if (i >= array2.length) {
                num1 = Integer.valueOf(array1[i]);
                num2 = 0;
            } else {
                num1 = Integer.valueOf(array1[i]);
                num2 = Integer.valueOf(array2[i]);
            }
            System.out.println("num1 = " + num1 + ", num2 = " + num2);
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }
}
