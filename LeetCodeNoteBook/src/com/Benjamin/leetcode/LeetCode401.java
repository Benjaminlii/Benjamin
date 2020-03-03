package com.Benjamin.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:LeetCode401
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 案例:
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *
 * 思路1:
 * 从0-11时,如果时的二进制1数量达到num,那么说明是整点
 * 否则从0-59遍历分,每次进行判断
 *
 * 思路2:
 * 初始化两个数组,分别为时和分对应下标的数值
 * 然后遍历0~num-1,进行组合即可
 *
 * @author: Benjamin
 * @date: 20-3-3 下午3:34
 */
public class LeetCode401 {
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            int count1FromI = count2(i);
            if (count1FromI == num) {
                ans.add(i + ":00");
            } else {
                for (int j = 0; j < 60; j++) {
                    if (count1FromI + count2(j) == num) {
                        ans.add(i + ":" + (j < 10 ? "0" + j : j));
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 计算num的二进制表示中1的个数
     */
    private static int count1(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>>= 1;
        }
        return count;
    }

    private static int count2(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode401().readBinaryWatch(1));
    }
}
