package com.Benjamin.offer;

import java.util.ArrayList;

/**
 * ClassName:Offer46
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-12-28 下午1:07
 */
public class Offer46 {
    int LastRemaining_Solution(int n, int m) {
        if (m == 0 || n == 0) {
            return -1;
        }
        ArrayList<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            data.add(i);
        }
        int index = -1;
        while (data.size() > 1) {
            index = (index + m) % data.size();
            data.remove(index);
            index--;
        }
        return data.get(0);
    }
}
