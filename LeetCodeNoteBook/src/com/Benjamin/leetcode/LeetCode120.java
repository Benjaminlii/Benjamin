package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode120
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * @author: Benjamin
 * @date: 19-11-9 下午6:23
 */
public class LeetCode120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            int length = triangle.get(i).size();
            for (int j = 0; j < length; j++) {
                List<Integer> nums = triangle.get(i);
                int row = i + 1;
                int num1 = triangle.get(row).get(j);
                int num2 = triangle.get(row).get(j + 1);
                int nextNum = num1 > num2 ? num2 : num1;
                triangle.get(i).set(j, triangle.get(i).get(j) + nextNum);
            }
        }
        return triangle.get(0).get(0);
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        lists.add(list);
        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        lists.add(list);
        list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(7);
        lists.add(list);
        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(8);
        list.add(3);
        lists.add(list);
        System.out.println(new LeetCode120().minimumTotal(lists));
    }
}
