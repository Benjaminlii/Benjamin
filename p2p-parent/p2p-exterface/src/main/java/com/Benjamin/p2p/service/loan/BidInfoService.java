package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.model.loan.BidInfo;

import java.util.List;

/**
 * 投资记录业务逻辑
 *
 * author:Benjamin
 * date:2019.7.26
 */
public interface BidInfoService {

    /**
     * 获取平台累计投资金额
     */
    Double queryAllBidMoney();

    /**
     * 根据产品id获取该产品的所有投资记录(包含用户信息)
     * @param loanId
     * @return
     */
    List<BidInfo> queryBidListByLoanId(Integer loanId);
}
