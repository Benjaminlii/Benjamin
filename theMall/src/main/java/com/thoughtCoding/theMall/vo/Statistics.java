package com.thoughtCoding.theMall.vo;

/**
 * 统计查询的返回值封装
 *
 * author:Benjamin
 * date:2019.7.30
 */
public class Statistics {
    /**
     * 统计的时间
     */
    private String time;

    /**
     * 该时间段内的销量
     */
    private Integer sum;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
