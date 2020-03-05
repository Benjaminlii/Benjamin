package com.Benjamin.leetcode;


/**
 * ClassName:LeetCode215
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 思路一:
 * 排序最大的k个元素,可以使用冒泡和选择排序
 * <p>
 * 思路二:
 * 小顶堆实现
 * 下将前k个元素初始化为最小堆
 * 从k~length遍历数组,每次如果下边处元素大于堆顶元素,那么替换堆顶元素,然后重新下沉堆顶元素
 *
 * @author: Benjamin
 * @date: 20-3-5 上午10:03
 */
public class LeetCode215 {
    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public int findKthLargest(int[] nums, int k) {
        // 从最后一个非叶子节点建立堆
        // 下标从0开始的堆,第一个非叶子节点应该是length/2
        for (int i = k / 2 ; i >= 0; i--) {
            adjustHeap(nums, i, k);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] <= nums[0]) {
                continue;
            }
            swap(nums, i, 0);
            adjustHeap(nums, 0, k);
        }
        return nums[0];
    }

    private static void adjustHeap(int[] array, int startSub, int length) {
        int sub = startSub;
        while (true) {
            int minSub = sub;
            if (sub * 2 < length && array[sub * 2] < array[minSub]) {
                minSub = sub * 2;
            }
            if (sub * 2 + 1 < length && array[sub * 2 + 1] < array[minSub]) {
                minSub = sub * 2 + 1;
            }
            if (minSub != sub) {
                swap(array, minSub, sub);
                sub = minSub;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode215().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));// 5
        System.out.println(new LeetCode215().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));// 4
        System.out.println(new LeetCode215().findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 5));// 3
        System.out.println(new LeetCode215().findKthLargest(new int[]{5, 2, 4, 1, 3, 6, 0}, 4));// 3
    }
}
