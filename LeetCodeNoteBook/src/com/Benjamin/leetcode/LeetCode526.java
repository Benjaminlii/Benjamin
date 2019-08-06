package com.Benjamin.leetcode;

/**
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * <p>
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * <p>
 *
 * 思路:全排列+剪枝
 *
 * author:Benjamin
 * date:2019.8.6
 */
public class LeetCode526 {
    public static void main(String[] args) {
        LeetCode526 leetCode526 = new LeetCode526();
        for (int i = 1; i < 15; i++) {
            System.out.println(i + " ---> " + leetCode526.countArrangement(i));
        }
    }

    public int countArrangement(int N) {
        if (N < 1) {
            return 0;
        }
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i + 1;
        }

        return getNum(array, 0, N);
    }

    public int getNum(int[] array, int start, int N) {
        if (start == N) {
            return 1;
        }
        int count = 0;

        for (int i = start; i < N; i++) {
            if (array[i] % (start+1) == 0 || (start+1) % array[i] == 0) {
                swap(array, start, i);
                count += getNum(array, start + 1, N);
                swap(array, start, i);
            }
        }
        return count;
    }

    public void swap(int[] nums, int sub1, int sub2) {
        int tmp = nums[sub1];
        nums[sub1] = nums[sub2];
        nums[sub2] = tmp;
    }
}
