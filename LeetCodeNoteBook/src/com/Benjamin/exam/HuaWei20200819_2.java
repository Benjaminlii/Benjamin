package com.Benjamin.exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName:HuaWei20200819_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-19 20:19
 */
public class HuaWei20200819_2 {

    public float fun(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    public int method(Map<Integer, Integer> map, int len) {
        double r = 1;
        int p = 0;
        int t2;
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(i)) {
                break;
            }
            t2 = map.get(i);
            int n = 2 * p;
            int m = t2;
            r *= fun(n) / ((fun(n - m) * fun(m)));
            p = t2;
        }
        r = r % (Math.pow(10, 9) + 7);
        return (int) r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int t1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            t1 = in.nextInt();
            if (map.containsKey(t1)) {
                map.put(t1, map.get(t1) + 1);
            } else {
                map.put(t1, 1);
            }
        }

        int ans = new HuaWei20200819_2().method(map, len);
        System.out.println(ans);
        in.close();
    }
}
