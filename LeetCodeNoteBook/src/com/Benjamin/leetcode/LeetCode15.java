package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 15. 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 解题思路:
 * 对数组进行排序后遍历,每次固定一个元素(i),然后从这个元素后面的一个元素(l)和最后一个元素(r)进行遍历
 * 如果三数之和不为0,判断sum的值,大于零r--,小于零l--
 * 如果三数之和为0,r++,添加结果集
 * 当r=l,i++,l=i+1,r=list.size()-1
 * 如果i>0,则三数都大于0,结束循环
 * 当sum=0时,判断l和r左侧和右侧相等的数,去重复
 * <p>
 * https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
 * <p>
 * author:Benjamin
 * date:2017.2.27
 */
public class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //先对数据进行排序
        List<Integer> list = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        int l, r;
        for (int i = 0; i < list.size() && list.get(i) <= 0; i++) {
            if (i > 0 && list.get(i - 1).equals(list.get(i))) {
                //去i的重复
                continue;
            }
            r = list.size() - 1;
            l = i + 1;
            while (l < r) {
                int sum = list.get(i) + list.get(l) + list.get(r);
                if (sum == 0) {
                    //i+l+r为0,r左移l右移,去重复
                    lists.add(Arrays.asList(list.get(i), list.get(l), list.get(r)));
                    while (l < r && list.get(l).equals(list.get(l + 1))) {
                        l++;
                    }
                    while (l < r && list.get(r).equals(list.get(r - 1))) {
                        r--;
                    }
                    r--;
                    l++;

                } else if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                }
            }
        }
        return lists;
    }

    /**
     * 复习,重写一遍
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int i = 0, l, r;
        int length = nums.length;
        // 三指针中的i是从前往后走的
        for (i = 0; i < length; i++) {
            // 当三个数中的最小值大于0,那么他们的和也不可能为0了
            if (nums[i] > 0) {
                break;
            }
            // 去掉i的重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            l = i + 1;
            r = length - 1;
            // 后面的l和r两个指针两边往中间走,因为是有序的
            while (l < r) {
                // 找到答案,那么添加结果集,去重复
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 去重复
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    // 移动指针,两边都需要移动,因为取了重复,移动一个的情况下不可能还是满足答案的
                    l++;
                    r--;
                } else if (sum > 0) { // 如果和大于0,那么吧右边的指针左移
                    r--;
                } else { // 相反,如果和小于0,那么右移左侧的指针
                    l++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode15().threeSum_(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
