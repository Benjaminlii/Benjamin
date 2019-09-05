package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode806
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，
 * 如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。
 * 我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。
 *
 * 现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
 *
 * @author: Benjamin
 * @date: 19-9-5 下午2:55
 */
public class LeetCode806 {

    public static void main(String[] args) {
        int widths[] = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String S = "abcdefghijklmnopqrstuvwxyz";
        int ans[] = new LeetCode806().numberOfLines(widths, S);

        System.out.println(ans[0] + ", " + ans[1]);
    }

    public int[] numberOfLines(int[] widths, String S) {
        int sum = 0;
        int high = 1;
        char chars[] = S.toCharArray();
        for (char aChar : chars) {
            if (sum + widths[aChar-'a'] > 100){
                sum = widths[aChar-'a'];
                high++;
            }else{
                sum += widths[aChar-'a'];
            }
        }

        return new int[]{high, sum};
    }
}
