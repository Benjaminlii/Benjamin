package com.Benjamin.exam;

/**
 * ClassName:SouGou20200905_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-09-05 19:38
 */
public class SouGou20200905_3 {

    public long getPasswordCount(String password) {
        char[] chars = password.toCharArray();
        int[] array = new int[chars.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = chars[i] - '0';
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += getPasswordCount(i, array, 0);
        }
        return ans;
    }

    private long getPasswordCount(int newNum, int[] password, int sub) {
        int ans = 0;
        if (sub >= password.length) {
            return 1;
        }
        int oldNum = password[sub];
        int number = newNum + oldNum;
        if (number % 2 == 1) {
            // 可以上下取整
            ans += getPasswordCount(number / 2 + 1, password, sub + 1);
        }
        ans += getPasswordCount(number / 2, password, sub + 1);

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SouGou20200905_3().getPasswordCount("12345"));
        System.out.println(new SouGou20200905_3().getPasswordCount("09"));
        System.out.println(new SouGou20200905_3().getPasswordCount("3"));
    }

}
