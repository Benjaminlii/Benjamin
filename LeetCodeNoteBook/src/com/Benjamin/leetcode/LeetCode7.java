package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode7
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author: Benjamin
 * @date: 2020-10-25 19:45
 */
public class LeetCode7 {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int flag = x >= 0 ? 1 : -1;
        int ans = 0;
        x = Math.abs(x);
        while (x != 0) {
            int num = x % 10;
            x /= 10;
            int newNum = ans * 10 + num;
            //如果数字溢出，直接返回0
            if ((newNum - num) / 10 != ans) {
                return 0;
            }
            ans = newNum;
        }
        return ans * flag;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode7().reverse(123));
        System.out.println(new LeetCode7().reverse(-123));
        System.out.println(new LeetCode7().reverse(120));
        System.out.println(new LeetCode7().reverse(1534236469));
    }
}
