package com.Benjamin.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:LeetCode406
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 解释一下：
 * 给出的数组是乱序的，要求恢复成满足h和k的序列
 * <p>
 * 思路：
 * 低的人随便排序是不影响高的人的k值的，那么我们根据身高从高到低进行组队
 *
 * @author: Benjamin
 * @date: 2020-08-07 14:08
 */
public class LeetCode406 {
    public int[][] reconstructQueue(int[][] people) {
        // 先排序
        // 高的人在前，低的人在后，相同身高则k值低的在前
        // 最终插入时，这个元素放的位置前面不能再插入可以影响当前元素k值的元素
        Arrays.sort(people, (people1, people2) -> people1[0] != people2[0] ? people2[0] - people1[0] : people1[1] - people2[1]);
        System.out.println(Arrays.deepToString(people));

        // 插入元素
        List<int[]> ans = new LinkedList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][2]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new LeetCode406().reconstructQueue(new int[][]{
                new int[]{7, 0},
                new int[]{4, 4},
                new int[]{7, 1},
                new int[]{5, 0},
                new int[]{6, 1},
                new int[]{5, 2}
        })));
    }
}
