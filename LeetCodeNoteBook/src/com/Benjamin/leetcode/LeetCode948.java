package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode948
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 你的初始能量为 P，初始分数为 0，只有一包令牌。
 * 令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
 * 如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
 * 如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
 * 在使用任意数量的令牌后，返回我们可以得到的最大分数。
 * <p>
 * 输入：tokens = [100,200,300,400], P = 200
 * 输出：2
 *
 * @author: Benjamin
 * @date: 19-10-24 上午10:35
 */
public class LeetCode948 {
    public int bagOfTokensScore(int[] tokens, int P) {
        int score = 0;
        int p = 0, q = tokens.length - 1;
        Arrays.sort(tokens);

        while (p <= q) {
            // 如果能量不够换取分数
            if (P < tokens[p]) {
                // 当前有没有分数
                if (score > 0) {
                    // 用最大的能量的牌获取能量并获取这张牌的分数,得到能量差
                    P += tokens[q--] - tokens[p++];
                }else{
                    // 能量不够且没分(第一张卡)
                    return score;
                }
            }else{
                P-=tokens[p++];
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode948().bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
    }
}
