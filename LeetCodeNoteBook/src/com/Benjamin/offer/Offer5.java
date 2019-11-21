package com.Benjamin.offer;


import java.util.Stack;

/**
 * ClassName:Offer5
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * @author: Benjamin
 * @date: 19-11-21 下午9:42
 */
public class Offer5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        stack1.push(node);
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
    }

    public int pop() {
        return stack1.pop();
    }
    public static void main(String[] args) {
        Offer5 demo = new Offer5();
        demo.push(1);
        demo.push(2);
        demo.push(3);
        demo.push(4);
        demo.push(5);
        demo.push(6);

        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
    }
}
