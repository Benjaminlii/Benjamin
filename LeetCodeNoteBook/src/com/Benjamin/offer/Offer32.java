package com.Benjamin.offer;

/**
 * ClassName:Offer32
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * <p>
 * 如果num1 + "" + num2 < num2 +　"" + num1,　那么认为num1 < num2
 * 用这个规则对数组进行排序即可
 *
 * @author: Benjamin
 * @date: 19-12-4 上午10:11
 */
public class Offer32 {
    public String PrintMinNumber(int[] numbers) {
        if (numbers.length == 0) {
            return "";
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < numbers.length - 1; i++) {
            int flag = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (Integer.valueOf(numbers[j] + "" + numbers[flag]) < Integer.valueOf(numbers[flag] + "" + numbers[j])) {
                    flag = j;
                }
            }
            int tmp = numbers[flag];
            str.append(numbers[flag]);
            numbers[flag] = numbers[i];
            numbers[i] = tmp;
        }

        str.append(numbers[numbers.length - 1]);
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Offer32().PrintMinNumber(new int[]{3, 32, 321}));
    }
}
