package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ByteDance20200412_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-4-12 下午8:35
 */
public class ByteDance20200412_1 {
    public static List<Integer> method(int[] array) {
        List<Integer> ans = new ArrayList<>();

        for (int sub = 0; sub < array.length; sub++) {
            int count = 0;
            for (int i = sub - 1; i >= 0; i--) {
                if (array[i] <= array[sub]) {
                    count++;
                } else {
                    break;
                }
            }
            for (int i = sub + 1; i < array.length; i++) {
                if (array[i] <= array[sub]) {
                    count++;
                } else {
                    break;
                }
            }
            ans.add(count);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(method(new int[]{1, 4, 3, 3}));
    }
}
