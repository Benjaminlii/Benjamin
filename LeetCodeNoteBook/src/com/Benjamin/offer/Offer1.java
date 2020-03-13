package com.Benjamin.offer;

/**
 * ClassName:Offer1
 * Package:com.Benjamin.offer
 * 剑指offer的刷题记录,本无序,这里按照热度指数降序排列
 * <p>
 * Description:
 * 二维数组中的查找
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 思路:
 * 暴力法肯定能想到
 * 问题在于整个数组好像不是有序的
 * 能想到的:
 * 暴力法优化
 * 多次二分 nlogn
 * 确定行的二分(在最右边找到最后一个小于等于targe的值,最左边找到最后一个小于target)
 * 最优解:
 * 从左下角开始,判断num(i,j下标处)和target的关系
 * 如果target<num,那么i--
 * 如果target>num,那么j++
 *
 * @author: Benjamin
 * @date: 19-11-18 上午8:36
 */
public class Offer1 {
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int i = 0, j = 0;
        int row = array.length, col = array[0].length;
        for (int subRow = 0; subRow < row; subRow++) {
//            System.out.println(">>> subWor = " + subRow);
            if (array[subRow][col - 1] <= target) {
//                System.out.println("i:" + i + " ->" + subRow);
                i = subRow;
            }
            if (array[subRow][0] <= target) {
//                System.out.println("j:" + j + " ->" + subRow);
                j = subRow;
            } else {
                break;
            }

        }
//        System.out.println("i = " + i + ", j = " + j);
        for (int k = i; k <= j; k++) {
            if (half(target, array[k])) {
                return true;
            }
        }
        return false;
    }

    public boolean half(int target, int[] array) {
        if (array == null) {
            return false;
        }
        int left = 0, right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target < array[mid]) {
                right = mid - 1;
            } else if (target == array[mid]) {
                return true;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] numss = new int[][]{
                new int[]{1, 2, 8, 9},
                new int[]{2, 4, 9, 12},
                new int[]{4, 7, 10, 13},
                new int[]{6, 8, 11, 15},
        };
        System.out.println(new Offer1().Find(1, numss));
    }
}
