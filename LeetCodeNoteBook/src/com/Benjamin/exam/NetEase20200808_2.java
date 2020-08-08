package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:NetEase20200808_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-08 15:27
 */
public class NetEase20200808_2 {

    public static int method(int[] array) {
        int count = 0;
        while (true) {
            // E
            int maxE = Integer.MIN_VALUE;
            int maxESub = 0;
            for (int i = 0; i < 2; i++) {
                if (maxE < array[i]) {
                    maxE = array[i];
                    maxESub = i;
                }
            }
            if (maxE == 0) {
                break;
            }
            if (array[0] == array[1]){
                maxESub = 0;
            }
            array[maxESub]--;

            // M
            int maxM = Integer.MIN_VALUE;
            int maxMSub = 0;
            for (int i = 1; i < 4; i++) {
                if (maxM < array[i]) {
                    maxM = array[i];
                    maxMSub = i;
                }
            }
            if (maxM == 0) {
                break;
            }
            if (array[1] == array[2] && array[2] == array[3]){
                maxMSub = 2;
            }
            array[maxMSub]--;

            // H
            int maxH = Integer.MIN_VALUE;
            int maxHSub = 0;
            for (int i = 3; i < 5; i++) {
                if (maxH < array[i]) {
                    maxH = array[i];
                    maxHSub = i;
                }
            }
            if (maxH == 0) {
                break;
            }
            if (array[3] == array[4]){
                maxHSub = 4;
            }
            array[maxHSub]--;

            count++;
//            System.out.println("subE = " + maxESub + ", subM = " + maxMSub + ", subH = " + maxHSub);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = new int[5];
        for (int i = 0; i < 5; i++) {
            array[i] = in.nextInt();
        }

        System.out.println(method(array));
        in.close();
    }

    public static void maina(String[] args) {

        System.out.println("2, 2, 1, 2, 2");
        System.out.println("0, 1, 2, 3, 4");
        System.out.println(method(new int[]{2, 2, 1, 2, 2}));
    }
}
