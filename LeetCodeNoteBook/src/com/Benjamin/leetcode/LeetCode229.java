package com.Benjamin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:LeetCode229
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-9-22 下午7:39
 */
public class LeetCode229 {

    public static void main(String[] args) {
        System.out.println(new LeetCode229().getHint("1807", "7810"));
        System.out.println(new LeetCode229().getHint("1123", "0111"));
    }

    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
            if (map.containsKey(secret.charAt(i) - '0')) {
                map.put(secret.charAt(i) - '0', map.get(secret.charAt(i) - '0') + 1);
            } else {
                map.put(secret.charAt(i) - '0', 1);
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            if (map.containsKey(guess.charAt(i) - '0') && map.get(guess.charAt(i) - '0') != 0) {
                map.put(guess.charAt(i) - '0', map.get(guess.charAt(i) - '0') - 1);
                cows++;
            }
        }

        return "" + bulls + "A" + (cows-bulls) + "B";
    }
}
