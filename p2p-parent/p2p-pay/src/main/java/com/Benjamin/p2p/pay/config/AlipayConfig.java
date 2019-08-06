package com.Benjamin.p2p.pay.config;

import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {

    private String alipayGetwayUrl;
    private String alipayAppid;
    private String alipayMerchantPrivateKey;
    private String alipayFormat;
    private String alipayCharset;
    private String alipayPublicKey;
    private String alipaySignType;

    private String returnUrl;
    private String notifyUrl;

    public String getAlipayGetwayUrl() {
        return alipayGetwayUrl;
    }

    public void setAlipayGetwayUrl(String alipayGetwayUrl) {
        this.alipayGetwayUrl = alipayGetwayUrl;
    }

    public String getAlipayAppid() {
        return alipayAppid;
    }

    public void setAlipayAppid(String alipayAppid) {
        this.alipayAppid = alipayAppid;
    }

    public String getAlipayMerchantPrivateKey() {
        return alipayMerchantPrivateKey;
    }

    public void setAlipayMerchantPrivateKey(String alipayMerchantPrivateKey) {
        this.alipayMerchantPrivateKey = alipayMerchantPrivateKey;
    }

    public String getAlipayFormat() {
        return alipayFormat;
    }

    public void setAlipayFormat(String alipayFormat) {
        this.alipayFormat = alipayFormat;
    }

    public String getAlipayCharset() {
        return alipayCharset;
    }

    public void setAlipayCharset(String alipayCharset) {
        this.alipayCharset = alipayCharset;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getAlipaySignType() {
        return alipaySignType;
    }

    public void setAlipaySignType(String alipaySignType) {
        this.alipaySignType = alipaySignType;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
