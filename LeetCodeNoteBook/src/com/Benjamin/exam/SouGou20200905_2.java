package com.Benjamin.exam;

/**
 * ClassName:SouGou20200905_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-05 19:17
 */
public class SouGou20200905_2 {

    public int getHouses(int t, int[] xa) {
        int ans = 2;
        for (int i = 0; i < xa.length / 2; i += 2) {
            double length = xa[i + 2] - xa[i];
            double num = length - (xa[i + 1] + xa[i + 3]) / 2.0;
            if (t == num) {
                ans += 1;
            } else if (t < num) {
                ans += 2;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SouGou20200905_2().getHouses(2, new int[]{-1, 4, 5, 2}));
    }

}
