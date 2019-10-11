package com.Benjamin.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:LeetCode593
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定二维空间中四点的坐标，返回四点是否可以构造一个正方形。
 * 一个点的坐标（x，y）由一个有两个整数的整数数组表示。
 * 示例:
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * <p>
 * 思路:
 * 每两个点只能有两种关系:连线为正方形的边(四条边长度相同)或者对角线(两条对角线长度相同)
 * 那么可以使用勾股定理计算所有点的距离都只能有两个结果,这样一定是正方形
 * 不能包括长度为0(两对重叠的点)
 *
 * @author: Benjamin
 * @date: 19-10-11 下午9:35
 */
public class LeetCode593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> set = new HashSet<>();
        set.add(getLength(p1, p2));
        set.add(getLength(p1, p3));
        set.add(getLength(p1, p4));
        set.add(getLength(p2, p3));
        set.add(getLength(p2, p4));
        set.add(getLength(p3, p4));

        return set.size()==2 && !set.contains(0);
    }

    public int getLength(int[] p1, int[] p2) {
        int a2 = p1[0] - p2[0];
        int b2 = p1[1] - p2[1];
        return a2 * a2 + b2 * b2;
    }
}
