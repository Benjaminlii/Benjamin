package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.model.loan.BidInfo;
import com.Benjamin.p2p.model.vo.BidUserTop;
import com.Benjamin.p2p.model.vo.ResultObject;

import java.util.List;
import java.util.Map;

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

    /**
     * 用户的投资操作
     * @param paraMap userId:用户id,loanId:产品id,bidMoney:投资金额
     */
    ResultObject invest(Map<String, Object> paraMap);

    /**
     * 从Redis缓存中获取用户排行榜
     * @return
     */
    List<BidUserTop> queryBidUserTop();
}
