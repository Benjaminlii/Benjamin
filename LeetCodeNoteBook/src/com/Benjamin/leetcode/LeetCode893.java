package com.Benjamin.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:LeetCode893
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 你将得到一个字符串数组 A。
 * 如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是特殊等价的。
 * 一次移动包括选择两个索引 i 和 j，且 i ％ 2 == j ％ 2，交换 S[j] 和 S [i]。
 * 现在规定，A 中的特殊等价字符串组是 A 的非空子集 S，这样不在 S 中的任何字符串与 S 中的任何字符串都不是特殊等价的。
 * 返回 A 中特殊等价字符串组的数量。
 *
 * @author: Benjamin
 * @date: 19-9-10 下午2:57
 */
public class LeetCode893 {

    private static final int NUMS[] = new int[]
            {2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public int numSpecialEquivGroups(String[] A) {
        Set<Integer> set = new HashSet();
        for (String s : A) {
            set.add(hash(s));
        }
        return set.size();
    }

    /**
     * 使用hash算法给每一个字符串的奇偶位置上的字符构成都计算一个值
     * 这里使用了整数分解为质数相乘
     * @param str 要进行哈希的字符串
     * @return 哈希值
     */
    private static int hash(String str) {
        int rtn = 1;
        for (int i = 0; i < str.length(); i += 2) {
            rtn *= NUMS[str.charAt(i) - 'a'];
        }
        rtn += 500;
        for (int i = 1; i < str.length(); i += 2) {
            rtn *= NUMS[str.charAt(i) - 'a'];
        }
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode893().numSpecialEquivGroups(
                new String[]{"abc", "acb", "bac", "bca", "cab", "cba"}));
    }
}
