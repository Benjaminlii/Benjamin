package com.Benjamin.p2p.service.loan;

public interface OnlyNumberService {

    /**
     * 获取Redis自增的全局唯一数字
     * @return
     */
    Long getOnlyNumber();
}
