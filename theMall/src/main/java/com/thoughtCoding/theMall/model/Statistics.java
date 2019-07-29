package com.thoughtCoding.theMall.model;

import java.util.Date;

/**
 * author:Benjamin
 * date:2019.7.19
 */
public class Statistics {
    /**
     * 出货信息统计id
     */
    private Short staId;

    /**
     * 店铺id
     */
    private Byte shopId;

    /**
     * 货物类型
     */
    private String staType;

    /**
     * 日期
     */
    private Date staDate;

    /**
     * 出货统计量
     */
    private Short staSum;

    public Short getStaId() {
        return staId;
    }

    public void setStaId(Short staId) {
        this.staId = staId;
    }

    public Byte getShopId() {
        return shopId;
    }

    public void setShopId(Byte shopId) {
        this.shopId = shopId;
    }

    public String getStaType() {
        return staType;
    }

    public void setStaType(String staType) {
        this.staType = staType;
    }

    public Date getStaDate() {
        return staDate;
    }

    public void setStaDate(Date staDate) {
        this.staDate = staDate;
    }

    public Short getStaSum() {
        return staSum;
    }

    public void setStaSum(Short staSum) {
        this.staSum = staSum;
    }
}