package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ClassName:Offer29
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 最小的K个数
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 *
 * 思路:
 * 使用堆排序实现,先构造一个k大小的最大堆,后面碰见比堆顶元素小的元素后,代替对顶元素,然后下沉
 *
 * @author: Benjamin
 * @date: 19-12-3 上午11:10
 */
public class Offer29 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (k > input.length){
            return arrayList;
        }
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            arrayList.add(input[i]);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        System.out.println(new Offer29().GetLeastNumbers_Solution(new int[]{8,7,6,5,4,3,2,1,0,3,6,21,6,37,23,2}, 5));
    }
}
