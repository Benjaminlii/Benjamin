package com.Benjamin.exam;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName:IQiYi20200823_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 15:48
 */
public class IQiYi20200823_2 {
    private static boolean method(String str) {
        if (str == null || str.length() == 0){
            return false;
        }
        Set<String> set = new HashSet<>();
        int rowSub = 0, colSub = 0;
        for (char c : str.toCharArray()) {
            if (c == 'N') {
                colSub++;
            } else if (c == 'S') {
                colSub--;
            } else if (c == 'E') {
                rowSub++;
            } else if (c == 'W') {
                rowSub--;
            }
            String node = rowSub+","+colSub;
            if (set.contains(node)){
                return true;
            }else{
                set.add(node);
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        in.close();
        System.out.println(method(str)?"True":"False");
    }
}
