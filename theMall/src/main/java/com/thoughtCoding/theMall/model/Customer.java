package com.thoughtCoding.theMall.model;

import java.util.Date;

/**
 * author:Benjamin
 * date:2019.7.19
 */
public class Customer {
    /**
     * 顾客id
     */
    private Integer customerId;

    /**
     * 顾客性别
     * 0为女性,1为男性
     */
    private Byte customerSex;

    /**
     * 顾客年龄
     */
    private Byte customerAge;

    /**
     * 顾客刷脸id
     */
    private String customerFaceId;

    /**
     * 顾客类型
     * 0为vip
     * 1为回头客
     * 2为普通顾客
     */
    private Byte customerType;

    /**
     * 顾客是否为黑名单
     * 0不是黑名单,1是黑名单
     */
    private Byte customerIsBlack;

    /**
     * 顾客上次被刷脸时间
     */
    private Date customerLastTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Byte getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(Byte customerSex) {
        this.customerSex = customerSex;
    }

    public Byte getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Byte customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerFaceId() {
        return customerFaceId;
    }

    public void setCustomerFaceId(String customerFaceId) {
        this.customerFaceId = customerFaceId;
    }

    public Byte getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Byte customerType) {
        this.customerType = customerType;
    }

    public Byte getCustomerIsBlack() {
        return customerIsBlack;
    }

    public void setCustomerIsBlack(Byte customerIsBlack) {
        this.customerIsBlack = customerIsBlack;
    }

    public Date getCustomerLastTime() {
        return customerLastTime;
    }

    public void setCustomerLastTime(Date customerLastTime) {
        this.customerLastTime = customerLastTime;
    }
}