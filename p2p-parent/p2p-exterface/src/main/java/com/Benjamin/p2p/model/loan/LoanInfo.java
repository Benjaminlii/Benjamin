package com.Benjamin.p2p.model.loan;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品实体
 */
public class LoanInfo implements Serializable {

    /**
     * 实现Serializable接口作用：可以将对象存到字节流中，然后再恢复
     * 由于项目属于分布式，需要通过网络传输，网络传输就得将对象转换为字节流，
     * 所以必须将对象进行序列化。如果项目不是分布式，那就没有必须
     */
    private static final long serialVersionUID = -5908687590191657719L;

    /**
     * 产品标识
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品利率
     */
    private Double rate;

    /**
     * 产品周期
     */
    private Integer cycle;

    /**
     * 产品发布时间
     */
    private Date releaseTime;

    /**
     *  产品类型
     *  0：新手宝产品
     *  1：优先产品
     *  2：散标产品
     */
    private Integer productType;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 产品募集资金（产品金额）
     */
    private Double productMoney;

    /**
     * 产品剩余可投金额
     */
    private Double leftProductMoney;

    /**
     * 起投金额（最小投资金额）
     */
    private Double bidMinLimit;

    /**
     * 单笔最大投资金额
     */
    private Double bidMaxLimit;

    /**
     *  产品状态
     *  0：未满标
     *  1：已满标
     *  2：满标且生成收益计划
     */
    private Integer productStatus;

    /**
     * 产品满标时间
     */
    private Date productFullTime;

    /**
     * 产品描述
     */
    private Integer version;

    /**
     * 产品版本号
     */
    private String productDesc;

    /**
     * 投资记录
     */
    public Integer getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Double getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(Double productMoney) {
        this.productMoney = productMoney;
    }

    public Double getLeftProductMoney() {
        return leftProductMoney;
    }

    public void setLeftProductMoney(Double leftProductMoney) {
        this.leftProductMoney = leftProductMoney;
    }

    public Double getBidMinLimit() {
        return bidMinLimit;
    }

    public void setBidMinLimit(Double bidMinLimit) {
        this.bidMinLimit = bidMinLimit;
    }

    public Double getBidMaxLimit() {
        return bidMaxLimit;
    }

    public void setBidMaxLimit(Double bidMaxLimit) {
        this.bidMaxLimit = bidMaxLimit;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getProductFullTime() {
        return productFullTime;
    }

    public void setProductFullTime(Date productFullTime) {
        this.productFullTime = productFullTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}