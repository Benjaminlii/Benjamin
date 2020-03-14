package com.Benjamin.testHashCode;

/**
 * ClassName:TestHashCode
 * Package:com.Benjamin.testHashCode
 * <p>
 * Description:
 * 测试==比较的到底是不是hashcode
 *
 * 结果显示,不是
 *
 * @author: Benjamin
 * @date: 20-3-14 下午8:22
 */
public class TestHashCode {
    public static void main(String[] args) {
        ClassA a = new ClassA();
        ClassA a1 = new ClassA();
        System.out.println(a.hashCode() == a1.hashCode());
        System.out.println(a==a1);
    }
}
