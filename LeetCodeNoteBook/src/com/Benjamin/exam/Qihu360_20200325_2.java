package com.Benjamin.exam;

/**
 * ClassName:Qihu360_20200325_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-24 下午8:22
 */
public class Qihu360_20200325_2 {
    public static double method(int n, int m) {
        if (n == 0 || m < 0) {
            return 0;
        }
        double ans = 0;
        ans += (n * 1.0) / (m + n);
        double notA = (1 - ans);
        m--;
        if (m > 0) {
            double notB = (1 - (n * 1.0) / (m + n));
            m--;
            double b1 = notA * notB * (m * 1.0) / (m + n) * method(n, m - 1);
            double b2 = notA * notB * (n * 1.0) / (m + n) * method(n - 1, m);
            ans += b1;
            ans += b2;
            return ans;
        }else{
            return ans;
        }

    }

    public static void main(String[] args) {
        System.out.println(method(2, 3));
        System.out.println(method(1, 3));
    }
}
