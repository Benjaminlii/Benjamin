package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode223
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 223. 矩形面积
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 * 示例:
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2
 * 输出: 45
 * <p>
 * 思路：
 * 直接两个矩形面积相加
 * 判断是否重合
 * 重合则再减去重叠部分面积
 * 判断重合：先假设重叠，找出左下角和右上角，然后判断是否合理（左下角横纵坐标都小于右上角）
 *
 * @author: Benjamin
 * @date: 2020-08-17 22:14
 */
public class LeetCode223 {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int aLeft = A;
        int aLow = B;
        int aRight = C;
        int aHigh = D;

        int bLeft = E;
        int bLow = F;
        int bRight = G;
        int bHigh = H;

        int ans = (aHigh - aLow) * (aRight - aLeft) + (bHigh - bLow) * (bRight - bLeft);

        // 这里假设重叠，找出左下角和右上角，然后判断是否合理
        int x1 = Math.max(aLeft, bLeft);
        int y1 = Math.max(aLow, bLow);
        int x2 = Math.min(aRight, bRight);
        int y2 = Math.min(aHigh, bHigh);
        if (x1 < x2 && y1 < y2) {
            ans -= (x2 - x1) * (y2 - y1);
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new LeetCode223().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
//        System.out.println(new LeetCode223().computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
//        System.out.println(new LeetCode223().computeArea(-2, -2, 2, 2, -3, -3, 3, -1));
//        System.out.println(new LeetCode223().computeArea(-1, -1, 1, 1, -2, -2, 2, 2));





        System.out.println((char) ('a' - 32));
    }
}
