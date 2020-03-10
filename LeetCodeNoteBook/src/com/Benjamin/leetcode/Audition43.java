package com.Benjamin.leetcode;

/**
 * ClassName:Audition43
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 面试题43. 1～n整数中1出现的次数
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 *
 * 思路:
 * 通过递归的思路,省略重复计算其中的f(99999)这样的内容
 * 大致思路:如果首尾为1,那么需要注意首尾为1的后面数字数量
 * 如果首尾不为1,那么首位的1就是999999这样的数量,然后加上其他的即可
 * 主要是由于n的不确定性,如果n是一百万这样的数,或者99999,就不需要考虑那么多
 *
 * @author: Benjamin
 * @date: 20-3-10 下午9:43
 */
public class Audition43 {
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }

        int ans = 0;

        String str = String.valueOf(n);
        // 最高位中完整的部分
        // 假设n = 23456, 则num = 9999
        int num = (int) Math.pow(10, str.length() - 1);
        // 最高位(1,2,3.....)
        // 最高位要完整(98 7654中完整的部分就是下面的90 0000)
        // 假设n = 23456, 则highest = 2
        int highest = str.charAt(0) - '0';
        // 后面不完整的部分
        // 假设n = 23456, 则last = 3456
        int last = n - highest*num;

        if (highest == 1) {
            // 最高位是1,例如12345
            // 首位为0的    0~999
            // 余数的首位   1开头的那2345个数
            // 余数中的1    后面2345中的1
            ans += countDigitOne(num - 1) + (last + 1) + countDigitOne(last);
        } else {
            // 最高位不为1 例如23456
            // 首位为1的()     1开头的9999
            // 除首位之外的1    0~19999中低四位出现的1
            // 不整齐的部分的1  3456中的1
            ans += num + (highest * countDigitOne(num - 1)) + countDigitOne(last);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Audition43().countDigitOne(1000000));
    }
}
