package com.Benjamin.offer;

/**
 * ClassName:Offer31
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 *
 * i /= 10 删除个位
 * i % 10 得到个位,判断,计数
 *
 * @author: Benjamin
 * @date: 19-12-4 上午10:01
 */
public class Offer31 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int j = i;
            while (j != 0){
                if (j % 10 == 1){
                    count++;
                }
                j /= 10;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Offer31().NumberOf1Between1AndN_Solution(13));
    }
}
