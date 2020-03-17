package com.Benjamin.exam;

import java.util.HashSet;
import java.util.Scanner;

/**
 * ClassName:Wanmeishijie20200317_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-17 下午7:41
 */
public class Wanmeishijie20200317_1 {

    // 代理服务器
    private static int hash(String str){
        String[] numbers = str.split("\\.");
        int num = 0;
        for (int j = 0; j < 4; j++) {
            int number = Integer.valueOf(numbers[j]);
            while (number != 0) {
                if (number / 2 == 1) {
                    num++;
                }
                number /= 2;
                num <<= 1;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        HashSet<Integer> servers = new HashSet<>();
        int numOfServer = in.nextInt();
        in.nextLine();
        for (int i = 0; i < numOfServer; i++) {

            servers.add(hash(in.nextLine()));
        }

        int numOfCall = in.nextInt();
        in.nextLine();
        int usingProxy = -1;
        int changeTime = -1;
        for (int i = 0; i < numOfCall; i++) {
            int number = hash(in.nextLine());
            if (servers.contains(number)){
                if (usingProxy != number){
                    changeTime++;
                    usingProxy = number;
                }
            }
        }
        System.out.println(changeTime);

    }
}
