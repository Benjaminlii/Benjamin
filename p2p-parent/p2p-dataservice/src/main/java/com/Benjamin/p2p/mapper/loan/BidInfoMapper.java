package com.Benjamin.p2p.mapper.loan;

import com.Benjamin.p2p.model.loan.BidInfo;

import java.util.List;

public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    /**
     * 获取平台累计投资金额
     */
    Double selectAllBidMoney();

    /**
     * 根据产品标识获取产品的所有投资记录(包含用户信息)
     * @param loanId
     * @return
     */
    List<BidInfo> selectBidInfoListByLoanId(Integer loanId);
}