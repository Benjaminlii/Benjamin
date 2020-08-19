package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName:LeetCode46
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * ..[1,2,3],
 * ..[1,3,2],
 * ..[2,1,3],
 * ..[2,3,1],
 * ..[3,1,2],
 * ..[3,2,1]
 * ]
 *
 * 思路：
 * 分治的思想
 * 把一个序列的全排列 分为其内部每一个元素 作首元素时 后续序列的所有全排列 的并集
 * 那么后续序列的全排列就可以看作是子问题，递归求解
 * 如何让其中的每一个元素分别充当首元素呢？
 * 和首元素交换 -> 充当首元素 -> 再次进行交换 -> 恢复成原来的状态
 * 对包括首元素本身的所有后续元素都进行上面的操作，就可以让每一个元素都充当一次首元素。
 *
 * 思考：
 * 如果用LinkedList，是不是头节点尾插到尾部就可以让头节点的后继节点充当首元素，重复这个过程length次就把所有元素都放在头节点上一次了
 * 但是这里中间遍历过程中start对应的并不是list头节点，删除效率略低。
 *
 * @author: Benjamin
 * @date: 2020-08-19 21:09
 */
public class LeetCode46 {
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return method(list, 0);
    }

    private static List<List<Integer>> method(List<Integer> list, int start) {
        List<List<Integer>> ans = new ArrayList<>();
        if (start == list.size() - 1) {
            ans.add(new ArrayList<>(list));
            return ans;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            ans.addAll(method(list, start + 1));
            Collections.swap(list, start, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode46().permute(new int[]{1, 2, 3}));
    }
}
