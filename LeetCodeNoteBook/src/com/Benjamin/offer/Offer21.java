package com.Benjamin.offer;

import java.util.Stack;

/**
 * ClassName:Offer21
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 * 使用一个栈进行入栈操作,同时指针遍历出栈顺序数组
 * 如果栈顶元素和当前遍历到的数组元素相等,那么弹栈,并且指针向后移动
 * 当栈为空并且指针为最后元素时,能匹配的上,否则都为不匹配
 *
 *
 * @author: Benjamin
 * @date: 19-11-29 上午9:52
 */
public class Offer21 {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 0){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i : pushA) {
            stack.push(i);
            while (stack.peek() == popA[j]){
                j++;
                stack.pop();
                if (stack.empty() &&j == popA.length){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Offer21().IsPopOrder(new int[]{}, new int[]{}));
    }
}
