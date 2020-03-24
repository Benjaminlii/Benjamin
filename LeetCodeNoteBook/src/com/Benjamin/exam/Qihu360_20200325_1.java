package com.Benjamin.exam;

/**
 * ClassName:Qihu360_20200325_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-24 下午8:14
 */
public class Qihu360_20200325_1 {

    public static int method(String str1, String str2) {
        int ans = 0;
        int AToT = 0;
        int TToA = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (str1.charAt(i) == 'A') {
                    AToT++;
                } else {
                    TToA++;
                }
                if (AToT > 0 && TToA > 0) {
                    AToT--;
                    TToA--;
                    ans++;
                }
            }
        }
        ans += AToT + TToA;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(method("ATTTAA", "TTAATT"));
        System.out.println(method("AAAAAAAAAATTTTTTTTTTTT", "TTTTTTTTTTAAAAAAAAAATT"));
        System.out.println(method("", ""));
    }
}
