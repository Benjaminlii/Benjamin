package com.Benjamin.others;

/**
 * ClassName:TestInteger
 * Package:com.Benjamin.others
 * <p>
 * Description:
 * 测试Integer比较相关细节
 * 在装箱获得Integer对象时，如果装箱的值在-128~127之间，重复的值会传回一样的对象
 * 原因是存在一个IntegerCatch，保存了这个范围的Integer对象，会重复利用
 * 而超出这个范围的对象，内次都会重新去new，生成的都是新对象
 *
 * @author: Benjamin
 * @date: 2020-08-19 17:19
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        System.out.println("i1 == 1 ->" + (i1 == 1));
        Integer i2 = new Integer(1);
        System.out.println("i1 == i2 ->" + (i1 == i2));
        Integer i3 = new Integer(300);
        System.out.println("i3 == 300 ->" + (i3 == 300));
        Integer i4 = 1;
        Integer i5 = 1;
        System.out.println("i4 == i5 ->" + (i4 == i5));
        Integer i6 = 200;
        Integer i7 = 200;
        System.out.println("i6 == i7 ->" + (i6 == i7));
        Integer i8 = Integer.valueOf(1);
        Integer i9 = Integer.valueOf(1);
        System.out.println("i8 == i9 ->" + (i8 == i9));
        Integer i10 = Integer.valueOf(200);
        Integer i11 = Integer.valueOf(200);
        System.out.println("i10 == i11 ->" + (i10 == i11));
    }
}
