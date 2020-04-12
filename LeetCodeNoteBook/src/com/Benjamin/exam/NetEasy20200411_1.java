package com.Benjamin.exam;

import java.util.Stack;

/**
 * ClassName:NetEasy20200411_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-4-11 下午7:20
 */
public class NetEasy20200411_1 {

    public static double method(String str1, String str2) {
        double a = Double.valueOf(str1);
        double b = Double.valueOf(str2);
        double c = a + b;
        char[] chs = (c + "").toCharArray();
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        stack.push('0');
        for (char ch : chs) {
            stack.push(ch);
        }

        while (!stack.empty()) {
            boolean flag = false;
            if (stack.peek() == '.') {
                sb.insert(0, stack.pop());
            }
            int num = stack.pop() - '0';
            if (num == 9 || num == 0) {
                num = num == 9 ? 0 : 1;
                if (stack.peek() == '.') {
                    stack.pop();
                    flag = true;
                }
                stack.push((char) (stack.pop() + 1));
            }
            sb.insert(0, num);
            if (flag) {
                sb.insert(0, ".");
            }
        }
        return Double.valueOf(sb.toString());

    }

    public static void main(String[] args) {
        System.out.println(method("1.28", "1.71"));
        System.out.println(method("15", "25"));
    }
}
