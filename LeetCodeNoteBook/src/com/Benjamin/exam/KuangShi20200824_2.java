package com.Benjamin.exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName:KuangShi20200824_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-24 15:55
 */
public class KuangShi20200824_2 {
    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 3);
    }

    private static int method_(int numOfNode) {
        for (int i = 0; i <= numOfNode; i++) {
            for (int j = 0; j <= numOfNode - i; j++) {
                int num1 = i;
                int num2 = j;
                int num3 = numOfNode - i - j;
                System.out.println(num1 + " - " + num2 + " - " + num3);
            }
        }
        return 0;
    }

    private static int method(int numOfNode) {
        int ans = 0;
        if (map.containsKey(numOfNode)) {
            ans = map.get(numOfNode);
            return ans;
        }
        numOfNode--;
        for (int i = 0; i <= numOfNode; i++) {
            for (int j = 0; j <= numOfNode - i; j++) {
                int num1 = i;
                int num2 = j;
                int num3 = numOfNode - i - j;
                int ans_ = 1;
                int ans1 = method(num1);
                if (ans1 != 0) {
                    ans_ *= ans1;
                }
                int ans2 = method(num2);
                if (ans2 != 0) {
                    ans_ *= ans2;
                }
                int ans3 = method(num3);
                if (ans3 != 0) {
                    ans_ *= ans3;
                }
                ans += ans_;
            }
        }
        map.put(numOfNode, ans);
        return ans;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(method(in.nextInt())%1000007);
        in.close();
    }
}
