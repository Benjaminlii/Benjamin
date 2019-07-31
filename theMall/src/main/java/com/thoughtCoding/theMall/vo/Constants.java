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
    public static final Object OK = "OK";

    /**
     * session中存储的user
     */
    public static final String SESSION_USER = "sessionUser";

    /**
     * 所有的UserType
     */
    public static final Set<Byte> USER_TYPE = new HashSet<>();
    static {
        USER_TYPE.add((byte) 0);
        USER_TYPE.add((byte) 1);
    }
}
