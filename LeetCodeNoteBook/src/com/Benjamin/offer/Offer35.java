package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer35
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 * <p>
 * 思路:
 * 首先肯定要对每一个元素进行比较
 * 那么避免重复的排序就是关键了
 * 使用归并排序,归并的过程中,就将元素一一比较过了
 *
 * @author: Benjamin
 * @date: 19-12-8 上午11:25
 */
public class Offer35 {

    public int InversePairs(int [] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int res = InversePairsCore(array, 0, array.length - 1);
        return  res % 1000000007;
    }

    private int InversePairsCore(int[] arr, int lo, int hi) {
        if (lo == hi) {
            return 0;
        }
        int mid = ((hi - lo) >> 1) + lo;
        int leftInversePairs = InversePairsCore(arr, lo, mid) % 1000000007;
        int rightInversePairs = InversePairsCore(arr, mid + 1, hi) % 1000000007;
        return  leftInversePairs + rightInversePairs + merge(arr, lo, mid, hi);
    }

    private int merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int index = 0;
        int count = 0;
        int p1 = lo, p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) {
            if (arr[p1] > arr[p2]) {
                count += mid - p1 + 1;
                if (count >= 1000000007) {
                    count %= 1000000007;
                }
                temp[index++] = arr[p2++];
            } else {
                temp[index++] = arr[p1++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= hi) {
            temp[index++] = arr[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[lo++] = temp[i];
        }

        return count % 1000000007;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 0};
        System.out.println(new Offer35().InversePairs(array));
        System.out.println(Arrays.toString(array));
    }

}
