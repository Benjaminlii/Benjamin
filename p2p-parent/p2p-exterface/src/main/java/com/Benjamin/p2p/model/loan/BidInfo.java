package com.Benjamin.p2p.model.loan;

import com.Benjamin.p2p.model.user.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 投资记录实体
 */
public class BidInfo implements Serializable {

    /**
     * 实现Serializable接口作用：将对象存在字节流中，然后恢复。
     * 由于项目属于分布式，需要通过网络传输，网络传输就得将对象转换为字节流，
     * 所以分布式必须将对象进行序列化。如果不是分布式，那就没有必要
     */
    private static final long serialVersionUID = 4095378177102769368L;

    /**
     * 投资记录标识
     */
    private Integer id;

    /**
     * 产品标识
     */
    private Integer loanId;

    /**
     * 用户标识
     */
    private Integer uid;

    /**
     * 投资金额
     */
    private Double bidMoney;

    /**
     * 投资时间
     */
    private Date bidTime;

    /**
     * 投资状态，默认为1成功
     */
    private Integer bidStatus;

    /**
     * 投资产品
     */
    private LoanInfo loanInfo;

    /**
     * 投资用户信息
     */
    private User user;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(Double bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}