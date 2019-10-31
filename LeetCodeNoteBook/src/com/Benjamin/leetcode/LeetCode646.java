package com.Benjamin.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ClassName:LeetCode646
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 示例 :
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 *
 * @author: Benjamin
 * @date: 19-10-31 下午2:33
 */
public class LeetCode646 {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

        int res = 1, tmp = pairs[0][1];
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > tmp){
                res++;
                tmp = pairs[i][1];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode646().findLongestChain(
                new int[][]{
                        new int[]{1, 2},
                        new int[]{2, 3},
                        new int[]{3, 4}
                })
        );
    }
}
