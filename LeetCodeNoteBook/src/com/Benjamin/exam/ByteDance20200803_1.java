package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ByteDance20200803_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 无序数组中某个元素比前面都大，后面都小
 *
 * @author: Benjamin
 * @date: 2020-08-03 20:36
 */
public class ByteDance20200803_1 {

    public static List method(int[] array){
        int[] flag = new int[array.length];
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue){
                maxValue = array[i];
                flag[i]++;
            }
        }
        for (int i = array.length-1; i >= 0; i--) {
            if (array[i] < minValue){
                minValue = array[i];
                flag[i]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 2){
                ans.add(array[i]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(method(new int[]{1,2,3,4,5,6}));
        System.out.println(method(new int[]{4,2,3,5,4,6,7}));
    }
}
