package com.Benjamin.offer;

/**
 * ClassName:Offer33
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 丑数
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 三指针法解决,具体看LeetCode264
 *
 * @author: Benjamin
 * @date: 19-12-5 下午12:21
 */
public class Offer33 {
    public int GetUglyNumber_Solution(int index) {
        if (index == 0){
            return 0;
        }
        int[] array = new int[index];
        array[0] = 1;
        int sub2 = 0, sub3 = 0, sub5 = 0;
        for (int i = 1; i < array.length; i++) {
            int num2 = array[sub2] * 2;
            int num3 = array[sub3] * 3;
            int num5 = array[sub5] * 5;
            array[i] = Math.min(Math.min(num2, num3), num5);
            // 这里不能用else if,可能会出现用一个向对应多个分解的情况
            // [1, 2, 3, 4, 5, 6, 6, 8, 9, 10]
            if (array[i] == num2) {
                sub2++;
            }
            if (array[i] == num3) {
                sub3++;
            }
            if (array[i] == num5) {
                sub5++;
            }
        }
        return array[index-1];
    }

    public static void main(String[] args) {
        System.out.println(new Offer33().GetUglyNumber_Solution(10));
    }
}
