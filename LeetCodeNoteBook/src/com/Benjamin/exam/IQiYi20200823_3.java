package com.Benjamin.exam;

import java.util.Scanner;
import java.util.Stack;

/**
 * ClassName:IQiYi20200823_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 16:01
 */
public class IQiYi20200823_3 {
    private static boolean method(String str) {
        Stack<Character> stack = new Stack<>();
        char[] chs = str.toCharArray();
        for (char ch : chs) {
            if (stack.empty()) {
                stack.push(ch);
            } else {
                if (ch == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (ch == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (ch == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }
        return stack.empty();
    }

    public static void main_(String[] args) {
        System.out.println(method("()()()(){}{}{}{}{}[][]"));
        System.out.println(method("({{{{([][][])}}}})"));
        System.out.println(method("()()()(){"));
        System.out.println(method("()()()({}[{}{[{[}]}]{})"));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        in.close();
        System.out.println(method(str) ? "True" : "False");
    }
}
