package com.Benjamin.others;

/**
 * 草稿
 *
 * @author: Benjamin
 * @Date: 2020.7.11
 */
public class Test {
    public static int getNumber() {
        int num;

        try {
            num = 1;
            return num;
        } finally {
            num = 2;
            return num;
        }
    }

    public static void main(String[] args) {
        System.out.println(getNumber());
    }
}
