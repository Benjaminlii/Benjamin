package com.Benjamin.offer;

import java.util.ArrayList;

/**
 * ClassName:Offer42
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 和为S的两个数字
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 * 思路:
 * 双指针,从两侧往中间移动,这样可以保证第一次得到的两个数的积最小
 *
 * @author: Benjamin
 * @date: 19-12-25 下午9:07
 */
public class Offer42 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int left = 0, right = array.length-1;
        boolean flag = false;

        while (left < right){

            int sumLeftRight = array[left] + array[right];
            if (sumLeftRight > sum){
                right--;
            }else if (sumLeftRight < sum){
                left++;
            }else{
                flag = true;
                break;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        if (!flag){
            return ans;
        }
        ans.add(array[left]);
        ans.add(array[right]);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Offer42().FindNumbersWithSum(new int[]{1,2,4,7,11,16}, 10));
    }
}
