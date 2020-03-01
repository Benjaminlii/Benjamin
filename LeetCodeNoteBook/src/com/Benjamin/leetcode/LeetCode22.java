package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode22
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 22. 括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * .."((()))",
 * .."(()())",
 * .."(())()",
 * .."()(())",
 * .."()()()"
 * ]
 * <p>
 * 思路一:
 * 递归＋剪枝吧
 * 向下二路递归,每次拼接一个左括号或者右括号
 * 当前层右括号不能小于左括号数量
 * 当左右括号数量都等于传入值时,证明可以成对出现了
 *
 * 思路二:
 * 使用动态规划的思想,dp[i]代表i对括号所有的组合
 * 那么dp[i] = "(" + dp[j] + ")" + dp[i-1-j], 其中j = 0, 1, 2, 3....i-1
 * 解释:i个括号的组合一定由左括号起始,然后其中会有一个右括号与其相对应,剩下的括号应该也是成对的
 * 那么剩下i-1对括号,可以分布在右括号的左右,进行组合即可
 *
 * @author: Benjamin
 * @date: 20-3-1 下午2:13
 */
public class LeetCode22 {

    // 思路一
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        method(ans, "", 0, 0, n);
        return ans;
    }
    public void method(List<String> ans, String str, int leftNum, int rightNum, int num) {
        if (rightNum > leftNum || rightNum > num || leftNum > num) {
            return;
        }
        if (leftNum == num && rightNum == num){
            ans.add(str);
        }
        method(ans, str+"(", leftNum+1, rightNum, num);
        method(ans, str+")", leftNum, rightNum+1, num);
    }

    // 思路二
    // 这种效率极底.......
    public List<String> generateParenthesis_(int n){
        if (n==0){
            return new ArrayList<>();
        }
        List<String>[] dp = new List[n+1];
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp[0] = dp0;

        for (int i = 1; i < n + 1; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp[j];
                List<String> str2 = dp[i-1-j];
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp[i] = cur;

        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode22().generateParenthesis_(3));
    }

}
