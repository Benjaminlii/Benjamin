package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:Offer54
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-1-3 下午4:59
 */
public class Offer54 {
    // 模拟流的缓存空间
    private ArrayList<Character> arrayList = new ArrayList<>();
    // 记录字符出现次数
    private HashMap<Character, Integer> hashMap = new HashMap<>();

    private int ansSub = -1;

    //Insert one char from stringstream
    public void Insert(char ch) {
        arrayList.add(ch);

        int times = 1;

        // 之前出现过该字符
        if (hashMap.containsKey(ch)) {
            times = hashMap.get(ch) + 1;
        }
        hashMap.put(ch, times);

        // 判断是否是当前认定为返回值的字符,或者当前没有可返回的字符,这时需要更新ansSub
        if ((ansSub == -1) || (hashMap.get(arrayList.get(ansSub)) > 1)) {
            int sub = -1;
            for (int i = ansSub + 1; i < arrayList.size(); i++) {
                if (hashMap.get(arrayList.get(i)) == 1) {
                    sub = i;
                    break;
                }
            }
            ansSub = sub;
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return ansSub != -1 ? arrayList.get(ansSub) : '#';
    }

    public static void main(String[] args) {
        Offer54 offer54 = new Offer54();
        offer54.Insert('B');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('a');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('b');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('y');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('B');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('a');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('b');
        System.out.println(offer54.FirstAppearingOnce());
        offer54.Insert('y');
        System.out.println(offer54.FirstAppearingOnce());
    }
}
