package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:KuaiShou20200322_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-22 下午7:10
 */
public class KuaiShou20200322_2 {

    public static List<Integer> method(List<Integer> list){
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean flag = false;
            int sub = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(j)>list.get(i)){
                    if (sub == -1){
                        flag = true;
                        sub = i;
                    }else{
                        flag = false;
                    }
                }
            }
            if (flag){
                ans.add(sub);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String inStr = in.nextLine();
        String[] strs = inStr.split(" ");

        for (String str : strs) {
            list.add(Integer.valueOf(str));
        }

        List<Integer> ans = method(list);

        if (ans.size() == 0){
            System.out.println(-1);
        }else{
            for (int i = 0; i < ans.size(); i++) {
                if (i == 0){
                    System.out.print(ans.get(i));
                }else{
                    System.out.print(" " + ans.get(i));

                }
            }
        }
    }

}
