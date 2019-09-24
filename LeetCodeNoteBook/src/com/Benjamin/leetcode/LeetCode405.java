package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode405
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-9-24 下午3:47
 */
public class LeetCode405 {

    public static void main(String[] args) {
        System.out.println(new LeetCode405().toHex(26666 ));
    }

    public String toHex(int num) {
        if (num == 0)
            return "0";
        String hex = "0123456789abcdef", ans = "";
        while (num != 0 && ans.length() < 8) {
            // num 与 1111 进行与运算得到的结果num最低四位的值
            // 从hex中取出对应的字符组成结果
            ans = hex.charAt(num & 0xf) + ans;
            num >>= 4;
        }
        return ans;
    }
}
