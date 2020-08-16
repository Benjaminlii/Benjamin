package com.Benjamin.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:QiAnXin20200816_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-16 15:27
 */
public class QiAnXin20200816_2 {


    // hello undo redo world.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();
        s.replaceAll("\t", " ");
        String[] strs = s.split(" ");
        List<String> doList = new LinkedList<>();
        List<String> undoList = new LinkedList<>();

        for (String str : strs) {
            if ("redo".equals(str)){
                doList.add(undoList.get(0));
                undoList.remove(0);
            }else if("undo".equals(str)){
                undoList.add(doList.get(doList.size()-1));
                doList.remove(doList.size()-1);
            }else{
                doList.add(str);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String str : doList) {
            sb.append(str).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb.toString());

    }
}
