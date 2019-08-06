package com.thoughtCoding.theMall;

import com.alibaba.fastjson.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Test3 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(2019, 0, 1));
        int numOfDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println(numOfDay);
    }
}
