package com.thoughtCoding.theMall.model;

import java.util.Date;

public class Record {
    /**
     * 出售记录id
     */
    private Integer recordId;

    /**
     * 店铺id
     */
    private Byte shopId;

    /**
     * 货物类型
     */
    private String recordType;

    /**
     * 货物id
     */
    private Short commodityId;

    /**
     * 顾客id
     */
    private Integer customerId;

    /**
     * 出售时间
     */
    private Date recordData;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Byte getShopId() {
        return shopId;
    }

    public void setShopId(Byte shopId) {
        this.shopId = shopId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Short getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Short commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getRecordData() {
        return recordData;
    }

    public void setRecordData(Date recordData) {
        this.recordData = recordData;
    }
}