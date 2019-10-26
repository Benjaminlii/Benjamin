package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode397
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 给定一个正整数 n，你可以做如下操作：
 * 1. 如果 n 是偶数，则用 n / 2替换 n。
 * 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 站在二进制的角度看这个数,除二相当于右移一位,加一减一都是在最低位进行操作
 * 那么最低位为0可以直接右移,最低位为两个1最高效率的操作是加一,最低位为一个一进行减一操作
 *
 * @author: Benjamin
 * @date: 19-10-26 下午3:18
 */
public class LeetCode397 {
    public int integerReplacement(int n) {
        int count = 0;

        while (n != 1) {
            System.out.println(Integer.toBinaryString(n));
            if ((n & 1) == 0) {// 最低位为0
                n >>>= 1;
                count++;
            } else {
                if((n & 2) == 0){
                    //01结尾最优则应当 用 n -1 取代 n
                    n -= 1;
                    count++;
                }else {
                    //11结尾除3这个特殊情况外，其余选用 n + 1取代 n，原因如上
                    if(n == 3){
                        //3的特殊性处理，原因如上
                        count+=2;
                        break;
                    }else {
                        n += 1;
                    }
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode397().integerReplacement(100000000));
    }
}
