package com.Benjamin.others;

import java.util.Arrays;

/**
 * ClassName:Sort
 * Package:com.Benjamin.others
 * <p>
 * Description:
 * 各种排序算法的集合demo
 * https://blog.csdn.net/weixin_41190227/article/details/86600821
 *
 * @author: Benjamin
 * @date: 20-2-19 下午8:40
 */
public class Sort {

    /**
     * 交换数组array中下标为a和b的两个值
     */
    public static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    /**
     * 冒泡排序
     * <p>
     * 从前向后遍历元素n-1次,每次都会把找到的最大的元素移动到最后的位置
     * 优化:如果一次遍历中没有发生位置交换,那么说明已经整体有序了
     * <p>
     * 时间复杂度:O(n^2)
     * 稳定性:稳定(在向后进行比较时,不等时标记向后移动,不进行交换,那么就是稳定的)
     */
    public static void bubbleSort(int[] array) {
        // 遍历length-1次,那么后length-1个元素都是有序的了,自然第一个元素也只能是有序的
        for (int i = 0; i < array.length - 1; i++) {
            // 每一个元素遍历到相应的位置就可以停下了,后面全都是有序的大元素
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     * <p>
     * 前i个数组都是有序的,每次在i+1至array.length-1的元素中找到最小的,和i+1处的元素进行交换
     * <p>
     * 时间复杂度:O(n^2)
     * 稳定性:稳定(每次找最小元素时,如果相等,不更换标记,那么就不会吧后面的相等的元素换到前面来)
     */
    public static void selectionSort(int[] array) {
        // 每次循环找出后面序列中最小的元素,用这个变量记录其下表
        int minSub;
        for (int i = 0; i < array.length - 1; i++) {
            minSub = i;
            // 在后面的序列中遍历,找最小的元素
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minSub]) {
                    minSub = j;
                }
            }
            // 最小的元素和i下标出的元素交换,然后这个边界向右移动
            swap(array, minSub, i);
        }
    }

    /**
     * 插入排序
     * <p>
     * 从前向后使用i为下标遍历数组,每次将i下标的元素拿出,将前面的比i下标元素小的元素一个一个向后移动一个单位,
     * 知道不能移动为止,然后将i下标的元素放在空出来的位置上(将这样的操作看作是向前一个一个元素的插入)
     * <p>
     * 时间复杂度:O(n^2)
     * 稳定性:稳定性:稳定(后面的元素向前插入时,不会插入到与自己一样大的元素之前)
     */
    public static void insertionSort(int[] array) {
        // 这里不能只遍历array.length-1次了,因为这样的话最后一个元素就根本没有参与到比较
        for (int i = 0; i < array.length; i++) {
            // 将遍历到的元素一直向前插队,直到不能向前插入为止
            int num = array[i];
            int j;
            for (j = i; j > 0 && num < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = num;
        }
    }

    /**
     * 希尔排序
     * 希尔排序是一种经过优化的插入排序
     * 设置一个步长i,初始值为array.length/2,每次使用步长分配整个数组为相互交叉的i个部分
     * 对每个部分使用插入排序,然后步长折半,直到步长为0时,跳出循环
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        // 步长
        for (int gace = array.length / 2; gace > 0; gace /= 2) {
            // 每种步长的情况下,0 ~ gace-1是每个部分的开头元素
            for (int i = 0; i < gace; i++) {
                // 按步长遍历这部分每一个元素
                for (int j = i; j < array.length; j += gace) {
                    int num = array[j];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{6, 5, 7, 3, 1, 8, 2, 4};
        insertionSort(array);
        System.out.println(Arrays.toString(array));

    }


}
