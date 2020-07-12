package com.Benjamin.offer;


import java.util.Stack;

/**
 * ClassName:Offer5
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 思路一:
 * 比较简单的就是每次入栈或者出栈的时候,将内部元素移动到另一个栈中,进行操作后再移回来
 *
 * 思路二:
 * 用一个栈入元素
 * 另一个栈出元素
 * 入元素的栈先进后出,出元素的栈先进后出,那么合起来就是先进先出
 *
 * @author: Benjamin
 * @date: 19-11-21 下午9:42
 */
public class Offer5 {
    // stack1用来入队
    // stack2用来出队
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
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
