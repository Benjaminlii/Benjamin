package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:KeDaXunFei20200829_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-29 19:27
 */
public class KeDaXunFei20200829_3 {
    private static String method(String str){
        String[] ss = str.split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            if (!"".equals(s)){
                sb.append(s).append("_");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in  =new Scanner(System.in);
        System.out.println(method(in.nextLine()));
        in.close();
    }
}
