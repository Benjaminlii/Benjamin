package com.Benjamin.offer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ClassName:Offer27
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
 * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *
 * 转化为字符数组,全排列
 *
 * @author: Benjamin
 * @date: 19-12-2 上午9:54
 */
public class Offer27 {
    public ArrayList<String> Permutation(String str) {
        char[] chars = str.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        if (str.equals("")){
            return arrayList;
        }
        recursive(arrayList, chars, 0, chars.length);
        Collections.sort(arrayList);
        return arrayList;
    }

    public boolean isRep(char[] a,int start,int i){   //判断从start-->i中有没有与i相同的元素,如果有证明i是重复的
        for(int k=start;k<i;k++){
            if(a[k]==a[i])
                return false;
        }
        return true;
    }

    public void recursive(ArrayList<String> arrayList, char[] chars, int start, int end){
        if (start == end){
            arrayList.add(new String(chars));
        }
        for (int i = start; i < end; i++) {
            if (!isRep(chars,start,i)){
                continue;
            }
            char tmp = chars[i];
            chars[i] = chars[start];
            chars[start] = tmp;

            recursive(arrayList, chars, start+1, end);

            tmp = chars[i];
            chars[i] = chars[start];
            chars[start] = tmp;
        }
    }


    public static void main(String[] args) {
        System.out.println(new Offer27().Permutation("abc"));
    }

}
