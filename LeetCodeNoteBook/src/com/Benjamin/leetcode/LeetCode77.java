package com.Benjamin.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:LeetCode77
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 思路:
 * 回溯法,参考全排列
 *
 * @author: Benjamin
 * @date: 20-2-7 下午2:26
 */
public class LeetCode77 {
    private int n, k;
    private List<List<Integer>> ans = new LinkedList<>();

    private void backtrack(int start, LinkedList<Integer> linkedList){
        if (linkedList.size() == k){
            ans.add(new LinkedList<>(linkedList));
            return;
        }

        for (int i = start; i <= n; i++){
            linkedList.add(i);
            backtrack(i+1, linkedList);
            linkedList.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<>());
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode77().combine(4, 2));
    }

}
