package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode756
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:金字塔转换矩阵
 * 现在，我们用一些方块来堆砌一个金字塔。 每个方块用仅包含一个字母的字符串表示，例如 “Z”。
 * 使用三元组表示金字塔的堆砌规则如下：
 * (A, B, C) 表示，“C”为顶层方块，方块“A”、“B”分别作为方块“C”下一层的的左、右子块。当且仅当(A, B, C)是被允许的三元组，我们才可以将其堆砌上。
 * 初始时，给定金字塔的基层 bottom，用一个字符串表示。一个允许的三元组列表 allowed，每个三元组用一个长度为 3 的字符串表示。
 * 如果可以由基层一直堆到塔尖返回true，否则返回false。
 * <p>
 * 思路:将所有button以List<List>的形式存储,内层 list用于存储"XXY"和"XXZ"这样的结构产生的两种结果
 *
 * @author: Benjamin
 * @date: 19-8-10 上午9:33
 */
public class LeetCode756 {

    public static void main(String[] args) {

        List<String> allowed = new ArrayList<>();
        allowed.add("XYD");
        allowed.add("YZE");
        allowed.add("DEA");
        allowed.add("FFF");

        System.out.println(new LeetCode756().pyramidTransition("XYZ", allowed));

    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        String bottoms[] = bottom.split("");
        List<List<String>> list = new ArrayList<>();
        for (String s : bottoms) {
            List a = new ArrayList<>();
            a.add(s);
            list.add(a);
        }
        int flag = 0;
        while (list.size() > 1) {
            flag = 0;
            for (int i = 0; i < list.size(); i++) {
                List a = new ArrayList<>();
                if (i == list.size() - 1) {
                    list.remove(i);
                    break;
                }
                int j = i + 1;
                for (String si : list.get(i)) {
                    for (String sj : list.get(j)) {
                        String ss = si + sj;
                        for (String s : allowed) {
                            if (s.substring(0, 2).equals(ss)) {
                                if (!a.contains(s.substring(2, 3))) {
//                                    System.out.println(">>> si+sj = " + ss + ", all = " + s);
                                    a.add(s.substring(2, 3));
                                    flag = 1;
                                }
                            }
                        }
                    }
                }
                list.set(i, a);
            }
            if (flag == 0) {
                break;
            }
//            System.out.println(list);
        }
        return flag == 1;
    }
}
