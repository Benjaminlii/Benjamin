package com.Benjamin.others;

import java.util.Arrays;

/**
 * ClassName:QSort
 * Package:com.Benjamin.others
 * <p>
 * Description:
 * 并发执行的快排
 *
 * @author: Benjamin
 * @date: 20-3-5 下午8:00
 */
public class QSort extends Thread {
    private int[] array;
    private int start;
    private int end;

    public QSort() {
    }

    // 线程的start函数没办法传参数,所以用成员变量和构造传参
    public QSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // 启动方法
    public int[] quickSort(int[] array) throws Exception {
        // end应该是数组的闭区间边界
        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] array, int start, int end) throws Exception {
        int sub = partition(array, start, end);
        Thread t1 = null;
        Thread t2 = null;
        if (sub > start) {
            // 子线程中向两边递归
            t1 = new QSort(array, start, sub - 1);
            t1.start();
        }
        if (sub < end) {
            // 子线程中向两边递归
            t2 = new QSort(array, sub + 1, end);
            t2.start();
        }
        // 等待子线程运行
        if (t1 != null) {
            t1.join();
        }
        if (t2 != null) {
            t2.join();
        }
        return array;
    }

    // 将array数组[start,end]闭区间内的元素进行分类,返回分隔下标
    private int partition(int[] array, int start, int end) {
        int flag = array[start];
        while (start < end) {
            while (start < end && array[end] > flag)
                end--;
            array[start] = array[end];
            while (start < end && array[start] < flag)
                start++;
            array[end] = array[start];
        }
        array[start] = flag;
        return start;
    }

    @Override
    public synchronized void start() {
        try {
            quickSort(array, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(new QSort().quickSort(new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8, 10})));
    }
}