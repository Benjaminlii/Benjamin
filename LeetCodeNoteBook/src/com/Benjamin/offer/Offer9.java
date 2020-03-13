package com.Benjamin.offer;

/**
 * ClassName:Offer9
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 思路:
 * 由于青蛙可以跳任意的高度,那么青蛙可以从之前的任意一个台阶跳上来,再加上从平底上跳上来这单独的一种
 * 第1级台阶:dp[1] = 1
 * 第2级台阶:dp[2] = dp[1] + 1 = 1 + 1 = 2
 * 第3级台阶:dp[3] = dp[1] + dp[2] + 1 = (1 + 2) + 1 = 3 + 1 = 4
 * 第4级台阶:dp[4] = dp[1] + dp[2] + dp[3] + 1 = (1 + 2 + 4) + 1 = 7 + 1 = 8
 * 第5级台阶:dp[5] = dp[1] + dp[2] + dp[3] + dp[4] + 1 = (1 + 2 + 4 + 8) + 1 = 15 + 1 = 16
 * 经总结:dp中的前面的元素都是2^n,那么前面n-1个元素相加就是2^(n-1-1),再加上平地上一步到达的1,简化公式就是2^(n-1)
 *
 * @author: Benjamin
 * @date: 19-11-23 上午10:49
 */
public class Offer9 {
    public int JumpFloorII(int target) {
        return (int) Math.pow(2,target-1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Offer9().JumpFloorII(i));
        }
    }
}
