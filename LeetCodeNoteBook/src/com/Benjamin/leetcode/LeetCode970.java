package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName:LeetCode970
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 强整数
 * 给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。
 * 返回值小于或等于 bound 的所有强整数组成的列表。
 * 你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。
 * <p>
 * 示例 1：
 * 输入：x = 2, y = 3, bound = 10
 * 输出：[2,3,4,5,7,9,10]
 * 解释：
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 *
 * 思路:
 * 遍历幂数,数量不超过给定值作为退出循环的条件
 * 注意单独判断xy同时为1,或者x,y其中有一个为1的情况即可
 *
 * @author: Benjamin
 * @date: 20-3-3 下午4:38
 */
public class LeetCode970 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        int xi, num;
        // 单独判断xy同时为1,或者x,y其中有一个为1的情况
        if (x == 1 && y == 1) {
            if (2 <= bound) {
                set.add(2);
            }
        } else if (x == 1) {
            for (int j = 0; (num = (int) Math.pow(y, j) + 1) <= bound; j++) {
                set.add(num);
            }
        } else if (y == 1) {
            for (int i = 0; (num = (int) Math.pow(x, i) + 1) <= bound; i++) {
                set.add(num);
            }
        } else {
            for (int i = 0; (xi = (int) Math.pow(x, i)) < bound; i++) {
                for (int j = 0; (num = xi + (int) Math.pow(y, j)) <= bound; j++) {
                    set.add(num);
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode970().powerfulIntegers(1, 1, 100));
    }
}
