package com.Benjamin.p2p.config;

/**
 * 放置调用API所用的的信息
 * <p>
 * author:Benjamin
 * date:2019.7.27
 */
public class Config {
    /**
     * 实名认证接口的key
     */
    private String realNameAppKey;

    /**
     * 实名认证接口的url
     */
    private String realNameUrl;

    public String getRealNameAppKey() {
        return realNameAppKey;
    }

    public void setRealNameAppKey(String realNameAppKey) {
        this.realNameAppKey = realNameAppKey;
    }

    public String getRealNameUrl() {
        return realNameUrl;
    }

    public void setRealNameUrl(String realNameUrl) {
        this.realNameUrl = realNameUrl;
    }
}
