package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode962
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-9-15 下午7:29
 */
public class LeetCode962 {
    public static void main(String[] args) {
        System.out.println(new LeetCode962().maxWidthRamp(new int[]{6,0,8,2,1,5}));
    }

    public int maxWidthRamp(int[] A) {
        int ans = A.length;
        while (true) {
            for (int i = 0; i <= A.length - ans; i++) {
                if (A[i] <= A[i + ans - 1]) {
//                    System.out.println("i = " + i + ", j = " + (i + ans - 1));
                    return ans - 1;
                }
            }
            ans--;
        }
    }
}
