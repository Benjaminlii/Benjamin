package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode932
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 漂亮数组有以下的性质:
 * （1）A是一个漂亮数组，如果对A中所有元素添加一个常数，那么Ａ还是一个漂亮数组。
 * （2）A是一个漂亮数组，如果对A中所有元素乘以一个常数，那么A还是一个漂亮数组。
 * （3）A是一个漂亮数组，如果删除一些A中所有元素，那么A还是一个漂亮数组。
 * （4) A是一个奇数构成的漂亮数组，B是一个偶数构成的漂亮数组，那么A+B也是一个漂亮数组
 * 比如:{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}也是一个漂亮数组。
 * <p>
 * 所以我们假设一个{1-m}的数组是漂亮数组，可以通过下面的方式构造漂亮数组{1-2m}:
 * 对{1-m}中所有的数乘以2-1，构成一个奇数漂亮数组A。如{1,3,2,4},可以得到{1,5,3,7}
 * 对{1-m}中所有的数乘以2,构成一个偶数漂亮数组B,如{1,3,2,4}, 可以得到{2,6,4,8}
 * A+B构成了{1-2m}的漂亮数组。{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}
 * 从中删除不要的数字即可。
 *
 * @author: Benjamin
 * @date: 19-11-1 上午10:33
 */
public class LeetCode932 {
    public int[] beautifulArray(int N) {
        if (N == 1) {
            return new int[]{1};
        }
        if (N == 2) {
            return new int[]{1, 2};
        }
        // 是否是偶数
        boolean isEven = N % 2 == 0;
        int[] array = new int[N];
        int[] front = beautifulArray(N % 2 == 0 ? N / 2 : N / 2 + 1);

        int sub = 0;
        for (int value : front) {
            array[sub++] = value * 2 - 1;
        }
        for (int value : front) {
            if (value * 2 >= N && !isEven) {
                continue;
            }
            array[sub++] = value * 2;
        }

        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LeetCode932().beautifulArray(4)));
    }
}
