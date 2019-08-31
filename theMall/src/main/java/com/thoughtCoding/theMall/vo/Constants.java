package com.thoughtCoding.theMall.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * 常量
 *
 * author:Benjamin
 * date:2019.7.30
 */
public class Constants {
    /**
     * 错误信息
     */
    public static final String ERROR_MESSAGE = "errorMessage";

    /**
     * OK
     */
    public static final String OK = "OK";

    /**
     * session中存储的user
     */
    public static final String SESSION_USER = "sessionUser";

    /**
     * 所有的UserType
     */
    public static final Set<Byte> USER_TYPE = new HashSet<>();

    /**
     * 神目api请求地址
     */
    public static final String SHENMU_URL = "https://api-bmw.deepcam.cn/api/vip";

    /**
     * action
     */
    public static final String ACTION = "action";

    /**
     * 公司id
     */
    public static final String COMPANY_ID = "59";

    /**
     * 访问秘钥
     */
    public static final String ACCESS_KEY = "3c61f53f63cd29a73d7ce822f4ee68cf";

    /**
     * 安全签名
     */
    public static final String SIGN = "sign";

    static {
        USER_TYPE.add((byte) 0);
        USER_TYPE.add((byte) 1);
    }

}
