package com.Benjamin.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * ClassName:Offer64
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组
 * {2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * <p>
 * {   [2,3,4]   ,2,6,2,5,1}，
 * {2,   [3,4,2]   ,6,2,5,1}，
 * {2,3,   [4,2,6]   ,2,5,1}，
 * {2,3,4,   [2,6,2]   ,5,1}，
 * {2,3,4,2,   [6,2,5]   ,1}，
 * {2,3,4,2,6,   [2,5,1]   }。
 * <p>
 * 思路:
 * 遍历一遍数组,每次先对队首元素进行判断是否出窗口,如果出窗口那么出队列头元素
 * 然后从队列后往前遍历,删掉小于当前元素的所有队尾元素.
 * 这样就可以保证队列中元素都是从大到小有序的
 * 那么相当于队列只保存当前窗口中从左到右最大的元素和其右侧的次大的元素,和在其次大的元素等等
 * 因为左侧的元素没有保存的意义了
 * 队列的长度不会超过窗口大小
 *
 * @author: Benjamin
 * @date: 20-1-16 下午2:45
 */
public class Offer64 {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        int begin;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < num.length; i++) {
            System.out.println(q);
            begin = i - size + 1;
            if (q.isEmpty())
                q.add(i);
            else if (begin > q.peekFirst())
                q.pollFirst();

            while ((!q.isEmpty()) && num[q.peekLast()] <= num[i])
                q.pollLast();
            q.add(i);
            if (begin >= 0)
                res.add(num[q.peekFirst()]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Offer64().maxInWindows(new int[]{7, 6, 5, 4, 3, 4, 1, 0}, 3));
    }
}
