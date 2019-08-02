package com.Benjamin.leetcode;

/**
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * author:Benjamin
 * date:2019.8.2
 */
public class LeetCode69 {

    public static void main(String[] args) {

        System.out.println(new LeetCode69().mySqrt(2147395600));
        System.out.println(new LeetCode69().mySqrt(0));
        System.out.println(new LeetCode69().mySqrt(1));
    }

    public int mySqrt(int x) {
        int rtn = 46340;
        int lift = 0;
        int right = 46340;
        int medium = 0;
        while (lift <= right) {
            medium = (lift + right) / 2;
            int mut = medium * medium;
            if (mut <= x && (medium + 1) * (medium + 1) > x){
                rtn = medium;
                break;
            }
            if(mut > x){
                right = medium-1;
            }else{
                lift = medium+1;
            }
        }
        return rtn;
    }

}
