package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode762
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
 * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
 * <p>
 * 思路:
 * 题目给出L和R都在10^6以内,换算成二进制也最多20位,那么1的数量最多就是20,所以求出的质数在2~20以内
 * 用位运算计算1的数量,然后在数组内查找是否存在即可
 *
 * @author: Benjamin
 * @date: 19-10-13 上午9:53
 */
public class LeetCode762 {
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        //0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20是不是质数
        int flag[] = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0};
        for (int i = L; i <= R; i++) {
            int count = 0;
            int num = i;
            while (num > 0){
                if ( num % 2 != 0){
                    count++;
                }
                num >>= 1;
            }
            ans += flag[count];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode762().countPrimeSetBits(10, 15));
    }
}
