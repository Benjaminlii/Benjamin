package com.thoughtCoding.alarm.model;

import org.springframework.stereotype.Component;

/**
 * ClassName:SMSConfig
 * Package:com.thoughtCoding.alarm.model
 * <p>
 * Description:
 * 调用短信接口所需要的隐私性参数,存储在config.properties文件中
 *
 * @author: Benjamin
 * @date: 19-10-17 下午9:37
 */
@Component
public class SMSConfig {
    private String secretId;

    private String secretKey;

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
