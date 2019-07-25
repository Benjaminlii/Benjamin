package com.Benjamin.p2p.model.loan;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值记录实体
 */
public class RechargeRecord implements Serializable {

    /**
     * 实现Serializable接口作用：将对象存放到字节流中，然后再恢复
     * 由于项目属于分布式，需要通过网络传输，那么，网络传输就得将对象转换为字节流，
     * 所以分布式项目中对象必须实现Serializable接口，如果不是分布式项目，就不必了
     */
    private static final long serialVersionUID = 7235619882863477473L;

    /**
     * 充值记录标识
     */
    private Integer id;

    /**
     * 用户标识
     */
    private Integer uid;

    /**
     * 充值订单号
     */
    private String rechargeNo;

    /**
     * 充值订单状态
     * 0：充值中
     * 1：充值成功
     * 2：充值失败
     */

    private String rechargeStatus;

    /**
     * 充值金额
     */
    private Double rechargeMoney;

    /**
     * 充值时间
     */
    private Date rechargeTime;

    /**
     * 充值描述
     */
    private String rechargeDesc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getRechargeDesc() {
        return rechargeDesc;
    }

    public void setRechargeDesc(String rechargeDesc) {
        this.rechargeDesc = rechargeDesc;
    }
}