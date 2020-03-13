package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:Offer54
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 题目描述
 * 字符流中第一个不重复的字符
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 * 思路:
 * 输入字符使用arrayList存储,字符出现的次数使用hashMap存储.
 * 输入是给arrayList后面添加元素,并判断当前标记输出的元素是否打破只出现一次的条件或者当前没有元素可以输出
 * 那么更新这个值,从ansSub往后寻找第一个只出现一次的元素,将其下表记录在ansSub中
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

        // 判断是否这时输出字符已经重复,或者当前没有可返回的字符,这时需要更新ansSub
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
