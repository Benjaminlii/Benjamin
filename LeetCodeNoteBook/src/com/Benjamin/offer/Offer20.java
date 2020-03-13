package com.Benjamin.offer;

import java.util.Stack;

/**
 * ClassName:Offer20
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * 最小值更新的时候存一份上一个最小值(在当前最小值入栈之前先将上一个最小值入栈)
 * 删除掉当前最小值的时候取出这个之前的最小值,更新min的值.
 *
 * @author: Benjamin
 * @date: 19-11-28 下午3:41
 */
public class Offer20 {
    private static Stack<Integer> stack = new Stack<Integer>();
    private int min = Integer.MAX_VALUE;

    public void push(int node) {
        if(stack.empty()){
            min = node;
            stack.push(node);
        }else{
            if(node <= min){
                stack.push(min);
                min = node;
            }
            stack.push(node);
        }
    }

    public void pop() {
        if (stack.empty()){
            return;
        }
        if (min == stack.peek()){
            stack.pop();
            min = stack.peek();
        }
        if (stack.size() == 1){
            min = Integer.MAX_VALUE;
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }

}
