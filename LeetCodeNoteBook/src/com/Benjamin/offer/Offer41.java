package com.Benjamin.offer;

import java.util.ArrayList;

/**
 * ClassName:Offer41
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 和为S的连续正数序列
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * <p>
 * 思路:
 * 双指针,用两个变量作为数组的两端(实际上从1开始的)
 * 使用公式计算两指针内部的数字之和,如果满足题意,添加到结果集中,左指针右移(窗口右移)
 * 如果计算结果小于,右窗口右移(增加一个数子)
 * 如果大于,左窗口右移
 *
 * @author: Benjamin
 * @date: 19-12-25 下午6:58
 */
public class Offer41 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int left = 1, right = 2;

        while (left < right) {
            int sumNow = (left + right) * (right - left + 1) / 2;
            if (sumNow == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i <= right; i++) {
                    list.add(i);
                }
                ans.add(list);
                left++;
            } else if (sumNow < sum) {
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Offer41().FindContinuousSequence(100));
    }
}
