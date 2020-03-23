package com.Benjamin.leetcode;

import java.util.Stack;

/**
 * ClassName:LeetCode20
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 思路:
 * 使用栈,很简单
 *
 * @author: Benjamin
 * @date: 20-3-23 下午5:23
 */
public class LeetCode20 {
    public boolean isValid(String s) {
        boolean ans = false;
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (stack.empty()) {
                stack.push(aChar);
            } else {
                char ch = stack.peek();
                if (aChar == '}' && ch == '{' || aChar == ']' && ch == '[' || aChar == ')' && ch == '('){
                    stack.pop();
                }else{
                    stack.push(aChar);
                }
            }
        }

        if (stack.empty()){
            ans = true;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode20().isValid("()"));
        System.out.println(new LeetCode20().isValid("()[]{}"));
        System.out.println(new LeetCode20().isValid("(]"));
        System.out.println(new LeetCode20().isValid("([]){}"));
    }
}
