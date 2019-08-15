package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode796
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定两个字符串, A 和 B。
 * A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
 *
 * 思路很微妙,只需要判断A串和B串的长度相等,且A+A串是否包含B串即可.因为A+A串已经包含了所有的移动情况.
 *
 * @author: Benjamin
 * @date: 19-8-15 上午10:29
 */
public class LeetCode796 {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
