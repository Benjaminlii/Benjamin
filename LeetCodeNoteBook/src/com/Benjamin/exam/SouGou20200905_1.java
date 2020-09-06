package com.Benjamin.exam;

/**
 * ClassName:SouGou20200905_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-05 18:45
 */
public class SouGou20200905_1 {
    public int numberofprize(int a, int b, int c) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b > c) {
            int tmp = b;
            b = c;
            c = tmp;
        }
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int ans = a;
        a -= ans;
        b -= ans;
        c -= ans;
//        System.out.println(ans + " " + a + " " + b + " " + c);
        if (a + b + c < 4) {
            return ans;
        }
        if (b == c) {
            a++;
            b--;
            c--;
        } else {
            a++;
            c -= 2;
        }
        ans += numberofprize(a, b, c);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SouGou20200905_1().numberofprize(4, 4, 2));
        System.out.println(new SouGou20200905_1().numberofprize(10, 3, 3));
        System.out.println(new SouGou20200905_1().numberofprize(13, 3, 3));
        System.out.println(new SouGou20200905_1().numberofprize(1000, 0, 0));
    }
}
