package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode739
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 思路:
 * 先试用暴力枚举得到答案
 * 再尝试用动态规划优化
 * 从右向左遍历
 * 最后一个标记为0
 * 当前数字如果大于前一个,前一个标1
 * 如果小于前一个,那么从当前数字的标记值开始向后计算
 *
 * @author: Benjamin
 * @date: 19-10-16 上午8:41
 */
public class LeetCode739 {
    public static void main(String[] args) {
        for (int an : new LeetCode739().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})) {
            System.out.print(an + " ");
        }
        System.out.println();
    }

    public int[] dailyTemperatures(int[] T) {
        int ans[] = new int[T.length];
        ans[T.length - 1] = 0;
        for (int i = T.length - 1; i > 0; i--) {
            // i为当前遍历到的节点,标记已知,j为前一个,当前循环计算这个值的标记
            int j = i - 1;
            if (T[i] > T[j]) {
                ans[j] = 1;
            } else {
                int sub = ans[i] != 0 ? ans[i]+i : i;

                while (sub < T.length && T[sub] <= T[j]) {
                    sub++;
                }
                if (sub < T.length) {
                    ans[j] = sub - j;
                }else{
                    ans[j] = 0;
                }
            }
        }
        return ans;
    }

    public int[] dailyTemperatures1(int[] T){
        int ans[] = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i+1; j < T.length; j++) {
                if (T[i] < T[j]){
                    ans[i] = j-i;
                    break;
                }
            }
        }
        return ans;
    }
}
