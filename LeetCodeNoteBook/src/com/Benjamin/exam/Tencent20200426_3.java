package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * ClassName:Tencent20200426_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-4-26 下午8:01
 */
public class Tencent20200426_3 {

    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();


        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        for (int j = 0; j < num; j++) {
            String str = in.nextLine();
            switch (str) {
                case "peek":
                    System.out.println(queue.getFirst());
                    break;
                case "poll":
                    queue.removeFirst();
                    break;
                default:
                    String[] strs = str.split(" ");
                    queue.addLast(Integer.valueOf(strs[1]));
            }

        }

    }
}

class Test{
    // stack1用来入队
    // stack2用来出队
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int poll() {
        if (stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek(){
        if (stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public static void main(String[] args) {
        Test list = new Test();

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        for (int j = 0; j < num; j++) {
            String str = in.nextLine();
            switch (str) {
                case "peek":
                    System.out.println(list.peek());
                    break;
                case "poll":
                    list.poll();
                    break;
                default:
                    String[] strs = str.split(" ");
                    list.push(Integer.valueOf(strs[1]));
            }

        }

    }
}
