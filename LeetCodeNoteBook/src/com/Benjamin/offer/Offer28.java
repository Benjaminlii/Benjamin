package com.Benjamin.offer;

/**
 * ClassName:Offer28
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * <p>
 * 思路1:
 * 从前往后遍历,每次记录前一个元素,当前遍历到元素一致时计数加一,否则计数减一
 * 如果计数器为0,那么更新前一个标记元素为当前元素.
 * 因为要找的元素要大于一半数量,所以这样遍历一遍要么找到这个元素,要么没有这样的元素找到了一个其他元素
 * 遍历进行判断即可
 * <p>
 * 思路2:
 * 先排序,后找到1/2下标处的元素,进行验证即可
 * <p>
 * 思路3:
 * 空间换时间
 * 遍历一遍,使用map存储出现次数,大于一半时返回
 * 遍历完之后说明没有出现这样的元素,返回0
 *
 * @author: Benjamin
 * @date: 19-12-2 下午1:44
 */
public class Offer28 {
    public int MoreThanHalfNum_Solution(int[] array) {
        int perNum = array[0];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (perNum == array[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    perNum = array[i];
                    count++;
                }
            }
        }

        count = 0;
        for (int value : array) {
            if (value == perNum) {
                count++;
            }
        }
        return (count > array.length / 2) ? perNum : 0;
    }

    public static void main(String[] args) {
        System.out.println(new Offer28().MoreThanHalfNum_Solution(new int[]{1,2,3,4,2,2,2,2,2,2,2,5,6,7,3,8,7,5,6,7}));
    }
}
