package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode923
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个整数数组 A，以及一个整数 target 作为目标值，返回满足 i < j < k 且 A[i] + A[j] + A[k] == target 的元组 i, j, k 的数量。
 * 由于结果会非常大，请返回 结果除以 10^9 + 7 的余数。
 * 示例 1：
 * 输入：A = [1,1,2,2,3,3,4,4,5,5], target = 8
 * 输出：20
 * 解释：
 * 按值枚举（A[i]，A[j]，A[k]）：
 * (1, 2, 5) 出现 8 次；
 * (1, 3, 4) 出现 8 次；
 * (2, 2, 4) 出现 2 次；
 * (2, 3, 3) 出现 2 次。
 *
 * @author: Benjamin
 * @date: 19-10-12 下午4:24
 */
public class LeetCode923 {
    public int threeSumMulti(int[] A, int target) {
        Arrays.sort(A);
        int ans = 0;
        for (int i = 0; i < A.length-2 && A[i]*3 <= target; i++) {
            for (int j = i + 1; j < A.length-1 && A[i] + A[j] + A[j] <= target; j++) {
                for (int k = A.length - 1; k > j && A[i] + A[j] + A[k] >= target; k--) {
                    if (A[i] + A[j] + A[k] == target) {
                        ans ++;
                    }
                }
            }
        }

        return ans;
    }
//    public int threeSumMulti(int[] A, int target) {
//        int ans = 0;
//        int countI = 0;
//        int countJ = 0;
//        for (int i = 0; i < A.length-2 && A[i]*3 <= target; i++) {
//            System.out.println("i = " + i);
//            if (i != 0 && A[i] == A[i - 1] && A[i] != A[i + 1]) {
//                ans += countI;
//                System.out.println(">>> A[i] = " + A[i] + ", countI = " + countI);
//                continue;
//            }
//            countI = 0;
//
//            for (int j = i + 1; j < A.length-1 && A[i] + A[j] + A[j] <= target; j++) {
//                System.out.println(" j = " + j);
//                if (j != i + 1 && A[j] == A[j - 1] && A[j] != A[j + 1]) {
//                    countI += countJ;
//                    System.out.println(">>> A[i] = " + A[i] + ", A[j] = " + A[j] + ", countJ = " + countJ);
//                    continue;
//                }
//                countJ = 0;
//
//                for (int k = A.length - 1; k > j && A[i] + A[j] + A[k] >= target; k--) {
//                    System.out.println("  k = " + k);
//                    if (A[i] + A[j] + A[k] == target) {
//                        countJ++;
//                        System.out.println(">>> A[i] = " + A[i] + ", A[j] = " + A[j] + ", A[k] = " + A[k]);
//                    }
//                }
//                countI += countJ;
//            }
//            ans += countI;
//        }
//
//        return ans;
//    }

    public static void main(String[] args) {
        System.out.println(new LeetCode923().threeSumMulti(new int[]{1,1,2,2,2,2}, 5));
    }
}