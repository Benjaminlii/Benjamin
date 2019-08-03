package com.Benjamin.p2p.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 得到日期date后days天的日期
     */
    public static Date getDateByAddDays(Date date, Integer days){

        //日期处理类
        Calendar instance = Calendar.getInstance();

        //设置时间
        instance.setTime(date);

        //添加时间
        instance.add(Calendar.DAY_OF_MONTH, days);

        return instance.getTime();
    }

    /**
     * 得到日期date后month月的日期
     */
    public static Date getDateByAddMonthes(Date date, Integer month){

        //日期处理类
        Calendar instance = Calendar.getInstance();

        //设置时间
        instance.setTime(date);

        //添加时间
        instance.add(Calendar.MONTH, month);

        return instance.getTime();
    }
}
