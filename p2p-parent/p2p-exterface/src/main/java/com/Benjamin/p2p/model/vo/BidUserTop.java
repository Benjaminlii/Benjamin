package com.Benjamin.p2p.model.vo;

import java.io.Serializable;

/**
 * 用户投资排行
 *
 * author:Benjamin
 * date:2019.8.3
 */
public class BidUserTop implements Serializable {

    /**
     * 用户手机号码(value)
     */
    private String phone;

    /**
     * 累计投资金额(score,刚好用于排序)
     */
    private Double score;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
