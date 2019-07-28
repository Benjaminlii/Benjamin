package com.Benjamin.p2p.model.vo;

import java.io.Serializable;

/**
 * author:Benjamin
 * date:2019.7.27
 */
public class ResultObject implements Serializable {

    /**
     * 错误码
     */
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
