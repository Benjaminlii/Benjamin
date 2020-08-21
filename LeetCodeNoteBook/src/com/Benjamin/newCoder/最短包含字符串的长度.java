package com.Benjamin.newCoder;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:最短包含字符串的长度
 * Package:com.Benjamin.newCoder
 * <p>
 * Description:
 * https://www.nowcoder.com/questionTerminal/58569ba19c05436e9eb492244b0902d8?orderByHotValue=1&mutiTagIds=645&page=1&onlyReference=false
 * 给定字符串str1和str2，求str1的字串中含有str2所有字符的最小字符串长度。
 * 示例1
 * 输入
 * abcde
 * ac
 * 输出
 * 3
 * 说明
 * “abc”中包含“ac”，且“abc”是所有满足条件中最小的。
 * <p>
 * 示例2
 * 输入
 * 12345
 * 344
 * 输出
 * 0
 * <p>
 * 思路：
 * 使用一个map存储要包含的字符串str2各个字符出现的次数
 * 用一个窗口遍历str1
 * 先向右移动right边界
 * 拿到一个字符，在map中对数量进行更新(如果不在待寻找字符记中。那么跳过)
 * 当map中全部不大于0之后，更新ans，并且开始向右移动left边界，更新ans
 * 当left边界使map中出现大于0的数时，开始移动right边界
 * 重复上述过程直到right边界越界
 *
 * @author: Benjamin
 * @date: 2020-08-21 16:29
 */
public class 最短包含字符串的长度 {

    private static int method(String str1, String str2) {
        Map<Character, Integer> str2Map = new HashMap<>(str2.length());
        for (char c : str2.toCharArray()) {
            if (str2Map.containsKey(c)) {
                str2Map.put(c, str2Map.get(c) + 1);
            } else {
                str2Map.put(c, 1);
            }
        }
        // 记录当前还有多少字符对应的数量大于零，也就是需要补充
        // 初始就是字符的全部数量之和
        int count = str2Map.size();

        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;
        while (right < str1.length()) {
            // 还有字符需要补充，那么移动right边界
            if (count > 0) {
                // right边界更新过程中，字符不影响结果，那么跳过
                if (str2Map.containsKey(str1.charAt(right))){
                    int numOfRight = str2Map.get(str1.charAt(right));
                    if (--numOfRight == 0) {
                        count--;
                    }
                    str2Map.put(str1.charAt(right), numOfRight);
                }
                right++;
            } else {
                // left边界更新过程中，字符不影响结果，那么跳过
                if (str2Map.containsKey(str1.charAt(left))){
                    // 字符需要填充的次数都不大于0，这时满足题目条件了
                    // 那么更新ans
                    ans = Math.min(ans, right - left);
                    // 向右移动left边界
                    int numOfLeft = str2Map.get(str1.charAt(left));
                    if (++numOfLeft > 0) {
                        count++;
                    }
                    str2Map.put(str1.charAt(left), numOfLeft);
                }
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(method("abcde","ac"));
        System.out.println(method("aaabsfdwewchghdefg","ac"));
        System.out.println(method("12345","344"));
    }
}
