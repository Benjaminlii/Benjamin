package com.Benjamin.p2p.service;

/**
 * author：Benjamin
 * date：2019.7.25
 */
public interface LoanInfoService {

    /**
     * 获取平台历史平均年化收益率
     * @return
     */
    double queryHistoryAverageRate();
}
