package com.thoughtCoding.theMall.model;

import java.util.Date;

public class BlackRecord {
    /**
     * 主键
     */
    private Integer recordId;

    /**
     * 顾客注册所用照片
     */
    private String faceUrl;

    /**
     * 刷脸帧图url
     */
    private String frameUrl;

    /**
     * 出镜的时间戳
     */
    private Date timestamp;

    /**
     * 出镜的顾客id
     */
    private Integer customerId;

    /**
     * 记录对应的顾客信息
     */
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl == null ? null : faceUrl.trim();
    }

    public String getFrameUrl() {
        return frameUrl;
    }

    public void setFrameUrl(String frameUrl) {
        this.frameUrl = frameUrl == null ? null : frameUrl.trim();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}