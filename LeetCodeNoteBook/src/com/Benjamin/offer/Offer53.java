package com.Benjamin.offer;

import java.math.BigDecimal;

/**
 * ClassName:Offer53
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 *
 * 思路:
 * 使用BigDecimal转换,如果抛异常,则不能转换
 *
 * @author: Benjamin
 * @date: 20-1-3 下午4:53
 */
public class Offer53 {
    public boolean isNumeric(char[] str) {
        try{
            new BigDecimal(new String(str));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
