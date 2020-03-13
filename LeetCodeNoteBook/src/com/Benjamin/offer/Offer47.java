package com.Benjamin.offer;

/**
 * ClassName:Offer47
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 求1+2+3+...+n
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * 思路:
 * 明摆着用递归
 *
 * @author: Benjamin
 * @date: 19-12-29 上午8:58
 */
public class Offer47 {
    public int Sum_Solution(int n) {
        int sum = 0;
        // n = 0时,直接返回0
        // n > 0时,向下递归
        boolean flag = (n > 0) && ((sum = n + Sum_Solution(n - 1)) > 0);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Offer47().Sum_Solution(5));
    }
}
