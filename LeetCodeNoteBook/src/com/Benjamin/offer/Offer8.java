package com.Benjamin.offer;

/**
 * ClassName:Offer8
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 思路:
 * 递归分治或者动态规划都可以
 *
 * @author: Benjamin
 * @date: 19-11-22 下午1:24
 */
public class Offer8 {
    public int JumpFloor(int target) {
        if (target <= 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println(new Offer8().JumpFloor(i));
        }
    }
}
