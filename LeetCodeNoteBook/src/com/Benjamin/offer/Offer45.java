package com.Benjamin.offer;

/**
 * ClassName:Offer45
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 扑克牌顺子
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,
 * “Oh My God!”
 * 不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),
 * “So Lucky!”。
 * LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
 * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 * <p>
 * 思路:
 * 最大值最小值(不计算0)之差小于5
 * 除0以外不重复
 *
 * @author: Benjamin
 * @date: 19-12-27 下午10:40
 */
public class Offer45 {
    public boolean isContinuous(int[] numbers) {
        if (numbers.length != 5){
            return false;
        }
        int min = 14, max = 0;
        int[] flag = new int[14];
        for (int number : numbers) {
            if (number != 0) {
                if (flag[number] == 1) {
                    return false;
                } else {
                    flag[number] = 1;
                }
                min = Math.min(min, number);
                max = Math.max(max, number);
            }
        }
        return max - min < 5;
    }

    public static void main(String[] args) {
        System.out.println(new Offer45().isContinuous(new int[]{1, 3, 0, 5, 0}));
    }
}
