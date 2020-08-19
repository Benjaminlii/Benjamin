package com.Benjamin.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName:HuaWei20200819_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-19 19:43
 */
public class HuaWei20200819_3 {
    public int method(int[] frame, int[] brick) {
        int moveTime = frame.length - brick.length;
        int maxRemove = Integer.MIN_VALUE;
        int hight = Integer.MIN_VALUE;
        for (int i : frame) {
            hight = Math.max(hight, i);
        }

        int[] frameCopy = Arrays.copyOfRange(frame, 0, frame.length);

        for (int i = 0; i <= moveTime; i++) {
            for (int j = 0; j < brick.length; j++) {
                frameCopy[i + j] += brick[j];
            }
            int min = Integer.MAX_VALUE;
            for (int num : frameCopy) {
                min = Math.min(min, num);
            }
            maxRemove = Math.max(maxRemove, min);
        }

        return hight - maxRemove;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String frameString = in.nextLine();
        String brickString = in.nextLine();

        char[] frameChars = frameString.toCharArray();
        int[] frame = new int[frameString.length()];
        for (int i = 0; i < frameChars.length; i++) {
            frame[i] = frameChars[i] - '0';
        }

        char[] brickChars = brickString.toCharArray();
        int[] brick = new int[brickString.length()];
        for (int i = 0; i < brickChars.length; i++) {
            brick[i] = brickChars[i] - '0';
        }

        System.out.println(new HuaWei20200819_3().method(frame, brick));
    }

    public static void main_(String[] args) {
        System.out.println(new HuaWei20200819_3().method(new int[]{2, 2, 0, 2}, new int[]{2}));
    }
}
