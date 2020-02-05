package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode191
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *
 * @author: Benjamin
 * @date: 20-2-5 下午2:44
 */
public class LeetCode191 {
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0){
            if ((n & 0b1) == 1){
                ans++;
            }
            n >>>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode191().hammingWeight(00000000000000000000000010010010));
    }
}
