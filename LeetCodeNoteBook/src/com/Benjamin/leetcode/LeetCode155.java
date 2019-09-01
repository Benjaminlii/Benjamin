package com.Benjamin.leetcode;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * <p>
 * author:Benjamin
 * date:2019.8.8
 */
public class LeetCode155 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }

    private static class MinStack {

        private int data;
        private MinStack next = null;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
        }

        public void push(int x) {
            MinStack minStack = new MinStack();
            minStack.data = x;
            minStack.next = this.next;
            this.next = minStack;
        }

        public void pop() {
            this.next = this.next.next;
        }

        public int top() {
            return this.next.data;
        }

        public int getMin() {
            MinStack minStack = this.next;
            int minNum = minStack.data;
            while(minStack != null){
                if (minNum > minStack.data){
                    minNum = minStack.data;
                }
                minStack = minStack.next;
            }
            return minNum;
        }
    }
}

