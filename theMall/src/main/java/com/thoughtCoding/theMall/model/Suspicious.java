package com.thoughtCoding.theMall.model;

import java.util.Date;

/**
 * author:Benjamin
 * date:2019.7.19
 */
public class Suspicious {
    /**
     * 可疑行为记录id
     */
    private Short susId;

    /**
     * 发生可疑行为的顾客id
     */
    private Integer customerId;

    /**
     * 可疑行为发生的时间
     */
    private Date susDatatime;

    public Short getSusId() {
        return susId;
    }

    public void setSusId(Short susId) {
        this.susId = susId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getSusDatatime() {
        return susDatatime;
    }

    public void setSusDatatime(Date susDatatime) {
        this.susDatatime = susDatatime;
    }
}