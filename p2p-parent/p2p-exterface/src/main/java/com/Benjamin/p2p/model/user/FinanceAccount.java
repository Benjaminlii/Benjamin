package com.Benjamin.p2p.model.user;

import java.io.Serializable;

/**
 * 用户资金账户实体
 */
public class FinanceAccount implements Serializable {

    /**
     * 实现Serializable接口作用：可以将对象存到字节流中，然后再恢复
     * 由于项目使用的是分布式，需要通过网络传输，网络传输就得将对象转换为字节流，
     * 所以必须将对象进行序列化。如果不是分布式项目，那就不必要
     */
    private static final long serialVersionUID = -1402081863472096872L;

    /**
     * 用户资金账户标识
     */
    private Integer id;

    /**
     * 用户标识
     */
    private Integer uid;

    /**
     * 用户可用金额
     */
    private Double availableMoney;

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

    public Double getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Double availableMoney) {
        this.availableMoney = availableMoney;
    }
}