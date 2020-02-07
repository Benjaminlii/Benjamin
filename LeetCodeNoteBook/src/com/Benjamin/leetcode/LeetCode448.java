package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode448
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 *
 * @author: Benjamin
 * @date: 20-2-7 下午1:58
 */
public class LeetCode448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] flag = new boolean[nums.length];

        for (int num : nums) {
            flag[num-1] = true;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                ans.add(i+1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode448().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
