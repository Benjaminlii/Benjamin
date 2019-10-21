package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode365
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * <p>
 * 思路:
 * 可以理解为ax+by = z(其中a和b有可能是负数,0或者1)
 * 暂定x,y的最小公倍数为g
 * x = n * g,  y = m * g
 * 化简为: (an+bm)*g = z
 * 所以只有an+bm为整数,也就是z整除g时,满足题意
 *
 * @author: Benjamin
 * @date: 19-10-21 上午8:31
 */
public class LeetCode365 {
    public int gcd(int p, int q) {
        System.out.println("p = " + p + ", q = " + q);
        if (p == 0) return q;
        int r = q % p;
        return gcd(r, p);
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        return z % gcd(x, y) == 0;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode365().canMeasureWater(3, 5, 4));
    }
}
