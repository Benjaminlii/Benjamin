package com.Benjamin.offer;

/**
 * ClassName:Offer10
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * 思路:
 * 理解一下题目,发现还是简单的跳台阶问题.递归直接解就ok了.
 *
 * @author: Benjamin
 * @date: 19-11-23 上午10:51
 */
public class Offer10 {
    public int RectCover(int target) {
        if (target <= 0){
            return 0;
        }else if (target == 1){
            return 1;
        }else if (target == 2){
            return 2;
        }
        return RectCover(target-1) + RectCover(target-2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Offer10().RectCover(i));
        }
    }
}
