package com.Benjamin.exam;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:Didi20200326_2
 * Package:com.Benjamin
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-26 下午4:39
 */
public class Didi20200326_2 {

    private static int method(String str) {

        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        int ans = -1;
        for (int i = 0; i < str.toCharArray().length; i++) {
            if (map.get(str.charAt(i)) == 1){
                ans = i;
                break;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(Didi20200326_2.method("123451234"));
    }

}