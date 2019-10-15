package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode914
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * <p>
 * 思路:
 * 找到所有数子出现的次数,找出它们的公约数,如果>=2,返回true
 * 公约数从2开始计算,如果出现让所有的次数都能整除,就找到了一个最小公约数,
 *
 * @author: Benjamin
 * @date: 19-10-15 上午10:44
 */
public class LeetCode914 {
    public static void main(String[] args) {
        System.out.println(new LeetCode914().hasGroupsSizeX(new int[]{1,1,1,1,2,2,2,2,2,2}));
    }

    public boolean hasGroupsSizeX(int[] deck) {
        Arrays.sort(deck);

        // 统计次数的数组
        int times[] = new int[deck.length];
        int length = 0;

        // 出现的最大次数
        int biggestTime = 0;
        // 用来标记每个元素,用于当前循环和上次循环的元素进行比较
        int flagNum = deck[0];
        for (int num : deck) {
            if (flagNum == num) {
                times[length]++;
                // 记录出现的最大次数,以减少后面循环的次数
                biggestTime = biggestTime > times[length] ? biggestTime : times[length];
            } else {
                flagNum = num;
                length++;
                times[length]++;
            }
        }
        length++;

        for (int i = 2; i <= biggestTime; i++) {
            boolean flag = true;
            for (int j = 0; j < length; j++) {
                if (times[j] % i != 0) {
                    flag = false;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
