package com.Benjamin.offer;

/**
 * ClassName:Offer37
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * 思路:
 * 二分找到这个数字,向前向后计数
 * 没找到返回0
 *
 * @author: Benjamin
 * @date: 19-12-12 下午6:35
 */
public class Offer37 {

    public int GetNumberOfK(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        int mid = (start + end) / 2;

        while (start <= end) {
            mid = (start + end) / 2;
            if (array[mid] == k) {
                break;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (start > end) {
            return 0;
        }
        int count = 1;
        for (int i = mid + 1; i < array.length && array[i] == k; i++) {
            count++;
        }
        for (int i = mid - 1; i >= 0 && array[i] == k; i--) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Offer37().GetNumberOfK(new int[]{3,3,3,3,4,5}, 3));
    }
}
