package com.Benjamin.exam;

import java.util.*;

/**
 * ClassName:LaoHu20200823_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 17:19
 */
public class LaoHu20200823_2 {
    /**
     * 合适的股票每手价格组合
     *
     * @param prices int整型一维数组 股票数量
     * @param m      int整型 资产值
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] prices, int m) {
        // write code here
        return combinationSum(prices, new ArrayList<Integer>(), 0, m);
    }

    private ArrayList<ArrayList<Integer>> combinationSum(int[] prices, List<Integer> list, int start, int m) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (start <= prices.length) {
            if (m == 0) {
                Collections.sort(list);
                ans.add(new ArrayList<>(list));
            } else if (start < prices.length) {
                List<Integer> list1 = new ArrayList<>(list);
                list1.add(prices[start]);
                ans.addAll(combinationSum(prices, list, start + 1, m));
                ans.addAll(combinationSum(prices, list1, start + 1, m - prices[start]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LaoHu20200823_2().combinationSum(new int[]{3, 8, 6, 3, 4, 4, 5}, 14));
    }
}
