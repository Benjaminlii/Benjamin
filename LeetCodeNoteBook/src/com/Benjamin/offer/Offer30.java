package com.Benjamin.offer;

/**
 * ClassName:Offer30
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 连续子数组的最大和
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
 * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？
 * (子向量的长度至少是1)
 *
 * 使用count存储当前累计和,如果累积和小于零,那么更新为下一个值
 * 如果大于零,那么相加
 * 每次判断过后,更新最大值
 *
 * @author: Benjamin
 * @date: 19-12-3 上午11:46
 */
public class Offer30 {
    public int FindGreatestSumOfSubArray(int[] array) {
        int max = Integer.MIN_VALUE;
        int count = array[0];
        for (int i = 1; i < array.length; i++) {
            if (count >= 0) {
                count += array[i];
            } else {
                count = array[i];
            }
            max = Math.max(count, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Offer30().FindGreatestSumOfSubArray(new int[]{-2, -8, -1, -5, -9}));
    }
}
