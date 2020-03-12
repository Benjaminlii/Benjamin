package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:MeiTuan20200312_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 小美曾经有一个特殊的数组，这个数组的长度为n。但是她在打恐怖游戏的时候被吓得忘记了这个数组长什么样了。不过她还记得这个数组满足一些条件。
 * 首先这个数组的每个数的范围都在L和R之间。包括端点。
 * 除此之外，这个数组满足数组中的所有元素的和是k的倍数。
 * 但是这样的数组太多了，小美想知道有多少个这样的数组。你只需要告诉她在模1e9+7意义下的答案就行了。
 * 输入
 * 一行四个整数n,k,L,R
 * （1≤n≤1e5    1≤k≤10    1≤L≤R≤1e9）
 * 输出
 * 输出一个数表示满足条件的数组的个数。
 * 样例输入
 * 9 1 1 3
 * 样例输出
 * 19683
 *
 * @author: Benjamin
 * @date: 20-3-12 下午8:17
 */
public class MeiTuan20200312_3 {
    private static int n;
    private static int k;
    private static int l;
    private static int r;

    private static Scanner in = new Scanner(System.in);

//    public static int method(){
//        // 最小和
//        int minSum = l*n;
//        int max = r;
//        // 可以插入的值
//        int num = r*n-minSum;
//    }

    public static void main(String[] args) {
        n = in.nextInt();
        k = in.nextInt();
        l = in.nextInt();
        r = in.nextInt();




    }
}
