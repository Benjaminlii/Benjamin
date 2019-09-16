package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode775
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 数组A是[0,1,...,N-1]的一种排列，N是数组A的长度。全局倒置指的是i,j满足0 <= i < j < N并且A[i] > A[j]
 * 局部倒置指的是i满足0 <= i < N并且A[i] > A[i+1]。
 * 当数组A中全局倒置的数量等于局部倒置的数量时，返回true。
 * <p>
 * 这个题就是找存不存在不是布局倒置的全局倒置
 * 那么什么情况下会出现这种情况?
 * 一个顺序序列要出现这种情况就必须让一个元素的偏移量大于1
 *
 * @author: Benjamin
 * @date: 19-9-16 下午5:04
 */
public class LeetCode775 {

    public static void main(String[] args) {
        System.out.println(new LeetCode775().isIdealPermutation(new int[]{1,2,0}));
    }

    public boolean isIdealPermutation(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}
