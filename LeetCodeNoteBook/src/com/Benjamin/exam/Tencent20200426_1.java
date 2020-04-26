package com.Benjamin.exam;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ClassName:Tencent20200426_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-4-26 下午8:01
 */
public class Tencent20200426_1 {
    public static void main(String[] args) {
        List<Integer> queue = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        int num1 = in.nextInt();
//        System.out.println("num1 = " + num1);
        in.nextLine();
        for (int i = 0; i < num1; i++) {

            int num2 = in.nextInt();
//            System.out.println("num2 = " + num2);
            in.nextLine();
            for (int j = 0; j < num2; j++) {
                String str = in.nextLine();
//                System.out.println("str = " + str);
                switch (str) {
                    case "TOP":
                        if (queue.size() > 0) {
                            System.out.println(queue.get(0));
                        } else {
                            System.out.println(-1);
                        }
                        break;
                    case "POP":
                        if (queue.size() > 0) {
                            queue.remove(0);
                        } else {
                            System.out.println(-1);
                        }
                        break;
                    case "SIZE":
                        System.out.println(queue.size());
                        break;
                    case "CLEAR":
                        queue.clear();
                        break;
                    default:
                        String[] strs = str.split(" ");
//                        System.out.println(strs[1]);
                        queue.add(Integer.valueOf(strs[1]));
                }

            }
            queue = new ArrayList<>();
        }

    }
}
