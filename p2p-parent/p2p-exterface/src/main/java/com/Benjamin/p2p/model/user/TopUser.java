package com.Benjamin.p2p.model.user;

import java.io.Serializable;

/**
 * 投资用户排行榜实体对象
 */
public class TopUser implements Serializable {

    /**
     * 实现Serializable接口作用：可以将对象存到字节流中，然后再恢复
     * 由于项目使用的是分布式，需要通过网络传输，网络传输就得将对象转换为字节流，
     * 所以必须将对象进行序列化。如果不是分布式项目，那就不必要
     */
    private static final long serialVersionUID = -8641829924916413676L;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户得分:投资所有产品总金额
     */
    private Double score;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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
