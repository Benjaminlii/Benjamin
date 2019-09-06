package com.Benjamin.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:LeetCode202
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 编写一个算法来判断一个数是不是“快乐数”。
 * <p>
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * @author: Benjamin
 * @date: 19-9-6 下午4:45
 */
public class LeetCode202 {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        char nums[] = null;
        int sum = 0;
        while (true) {
            nums = (n + "").toCharArray();
            sum = 0;
            for (char num : nums) {
                sum += Math.pow(num - '0', 2);
            }
            if (sum == 1) {
                return true;
            }
            if (set.contains(sum)) {
                return false;
            }
            n = sum;
            set.add(sum);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode202().isHappy(215));
    }
}
