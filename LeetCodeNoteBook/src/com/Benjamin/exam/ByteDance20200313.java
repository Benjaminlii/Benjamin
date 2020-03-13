package com.Benjamin.exam;


/**
 * ClassName:ByteDance20200313
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 字节跳动2020.03.13一面算法
 * 循环有序数组中查找一个值,返回下标
 * 循环数组类似:5,6,7,8,9,0,1,2,3,4
 *
 * 思路:
 * 对二分查找进行改造
 * 二分之后先判断二分处是不是要找的值,如果是字节返回
 * 如果不是,那么判断那一侧是顺序的(通过二分处的值和两边的大小比较得到)
 * 再在内部判断处于哪一边,因为只有有序的一侧才能判断出是否包含要寻找的key,如果不在那就另一侧
 * 里面的判断要加上等号,关于mid值不需要因为判断过了
 *
 * @author: Benjamin
 * @date: 20-3-13 下午4:58
 */
public class ByteDance20200313 {

    public int method(int[] nums, int key) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            // 直接找到要找的值
            if (nums[mid] == key) {
                return mid;
            }
            // 根据二分的位置判断左侧或者右侧是有序的
            if (nums[start] < nums[mid]) {// 左侧有序,右侧循环有序
                // 在左侧
                if (key < nums[mid] && key >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            } else if (nums[mid] < nums[end]) {// 右侧有序,左侧循环有序
                // 右侧顺序
                if ((key > nums[mid]) && key <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 7, 8, 9, 1, 2, 3, 4};
        System.out.println(new ByteDance20200313().method(nums, 1));
    }
}
