package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * <p>
 *
 * author:Benjamin
 * date:2019.7.26
 */
public class LeetCode51 {
    public static void main(String[] args) {
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(3);
//        arrayList.add(1);
//        arrayList.add(4);
//        arrayList.add(2);
//        System.out.println(arrayList);
//        System.out.println(toStringList(arrayList));
        System.out.println(new LeetCode51().solveNQueens(4));
    }

    /**
     * 使用全排列可以直接得到满足行和列上不重复的所有解,判断斜线上是否满足要求即可
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> rtn = new ArrayList<>();

        //初始化数组
        List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numberList.add(i);
        }

        //进行全排列
        List<List<Integer>> lists = new ArrayList<>();
        fullPermutation(lists, numberList, 0);

        //判断
        for (List<Integer> list : lists) {
            if (check(list)) {
                rtn.add(toStringList(list));
            }
        }

        return rtn;
    }

    //全排列算法
    public static void fullPermutation(List<List<Integer>> lists, List<Integer> numberList, int flag) {
        if (flag == numberList.size() - 1) {
            lists.add(new ArrayList<>(numberList));
        } else {
            for (int i = flag + 1; i < numberList.size(); i++) {
                swap(numberList, flag, i);
                fullPermutation(lists, numberList, flag + 1);
                swap(numberList, flag, i);
                fullPermutation(lists, numberList, flag + 1);
            }
        }
    }

    /**
     * 检查是否满足斜线上不在一条线
     */
    public static boolean check(List<Integer> numberList) {
        //对每一个皇后进行判断
        for (int i = 0; i <= numberList.size(); i++) {
            //当前遍历到的皇后右侧的皇后不能出现在一条斜线上
            for (int j = i + 1; j < numberList.size(); j++) {
                if (numberList.get(j) == numberList.get(i) + (j - i)
                        || numberList.get(j) == numberList.get(i) - (j - i)) {
//                    System.out.println(numberList + ", numberList.get(" + i + ") = " + numberList.get(i) + ", j = " + j);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 将数字list转化为字符串list
     */
    public static List<String> toStringList(List<Integer> numberList) {
        List<String> rtn = new ArrayList<>();
        List<StringBuffer> sbs = new ArrayList<>();
        for (int i = 0; i < numberList.size(); i++) {
            sbs.add(new StringBuffer());
        }

        for (int i = 0; i < numberList.size(); i++) {
            for (int j = 0; j < numberList.size(); j++) {
                if (j + 1 == numberList.get(i)) {
                    sbs.get(j).append("Q");
                } else {
                    sbs.get(j).append(".");
                }
            }
        }

        for (StringBuffer sb : sbs) {
            rtn.add(sb.toString());
        }

        return rtn;
    }

    /**
     * 交换list中a和b位置上的元素
     */
    public static void swap(List<Integer> list, int a, int b) {
        int tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}
