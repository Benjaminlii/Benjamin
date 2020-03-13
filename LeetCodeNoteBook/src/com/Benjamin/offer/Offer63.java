package com.Benjamin.offer;

import java.util.TreeSet;

/**
 * ClassName:Offer63
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 数据流中的中位数
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 * @author: Benjamin
 * @date: 20-1-16 下午2:32
 */
public class Offer63 {
    private TreeSet<Integer> treeSet = new TreeSet<>();
    private boolean flag = true;
    private int count;

    public void Insert(Integer num) {
        treeSet.add(num);
        flag = !flag;
        count++;
    }

    public Double GetMedian() {
        if (count == 0){
            return 0.0;
        }
        Object[] integers = treeSet.toArray();
        Double ans = 0.0;
        if (flag) {
            ans = ((Integer)integers[count / 2] + (Integer)integers[count / 2 - 1]) / 2.0;
        } else {
            ans = (Integer)integers[count / 2] * 1.0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Offer63 offer63 = new Offer63();
//        offer63.Insert(1);
//        offer63.Insert(8);
//        offer63.Insert(6);
//        offer63.Insert(3);
//        offer63.Insert(7);
//        offer63.Insert(5);
//        offer63.Insert(2);
//        offer63.Insert(4);
        System.out.println(offer63.GetMedian());
    }
}
