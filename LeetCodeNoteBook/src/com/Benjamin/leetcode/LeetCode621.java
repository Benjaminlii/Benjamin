package com.Benjamin.leetcode;

import java.util.Arrays;

/**
 * ClassName:LeetCode621
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 * 示例 ：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * 提示：
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 * <p>
 * 思路：
 * 先统计数量，然后把数量排个序，这里字母已经没有意义了，不用管
 * 冷却时间为n
 * 进行调度时，每次调度n+1个任务为一组
 * 每组从数量最多的开始调度，任务次数不为0时，任务次数--，没命中视为待命状态
 * 每次调度已调度时间++
 *
 * @author: Benjamin
 * @date: 2020-08-18 20:42
 */
public class LeetCode621 {
    public int leastInterval(char[] tasks, int n) {
        int[] array = new int[26];
        for (char task : tasks) {
            array[task - 'A']++;
        }
        Arrays.sort(array);

        int ans = 0;
        while (array[25] > 0) {
            int i = 0;
            while (i <= n) {
                // 应对 0，0，0，0，0.。。。。0，0，0，1的情况
                if (array[25] == 0) {
                    break;
                }
                if (i < 26 && array[25 - i] > 0) {
                    array[25 - i]--;
                }
                ans++;
                i++;
            }
            Arrays.sort(array);
        }
        return ans;
    }

    /**
     * 解题思路：
     * 1、将任务按类型分组，正好A-Z用一个int[26]保存任务类型个数
     * 2、对数组进行排序，优先排列个数（count）最大的任务，
     *      如题得到的时间至少为 retCount =（count-1）* (n+1) + 1 ==> A->X->X->A->X->X->A(X为其他任务或者待命)
     * 3、再排序下一个任务，如果下一个任务B个数和最大任务数一致，
     *      则retCount++ ==> A->B->X->A->B->X->A->B
     * 4、如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，因为间隔长度肯定会大于n，在这种情况下就是任务的总数是最小所需时间
     *
     */
    public int leastInterval_(char[] tasks, int n) {
        if (tasks.length <= 1 || n < 1) return tasks.length;
        //步骤1
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        //步骤2
        Arrays.sort(counts);
        int maxCount = counts[25];
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        //步骤3
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        //步骤4
        return Math.max(retCount, tasks.length);
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode621().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(new LeetCode621().leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
        System.out.println(new LeetCode621().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
    }
}
