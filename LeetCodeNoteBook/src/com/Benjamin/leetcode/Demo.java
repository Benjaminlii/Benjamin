package com.Benjamin.leetcode;


/**
 * ClassName:Demo
 * Package:com.Benjamin.leetcode
 * <p>
 * Description: just test
 *
 * @author: Benjamin
 * @date: 19-8-8 下午5:33
 */
public class Demo {
    public static void main(String[] args) {

        int cb = Integer.SIZE-3;

        int running = -1 << cb;
        int shutdown = 0 << cb;
        int stop = 1 << cb;
        int tidying = 2 << cb;
        int terminated = 3 << cb;
        System.out.println(running);
        System.out.println(shutdown);
        System.out.println(stop);
        System.out.println(tidying);
        System.out.println(terminated);
        System.out.println("hello world, ha?");
    }
}
