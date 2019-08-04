package com.Benjamin.p2p.mapper.loan;

import com.Benjamin.p2p.model.loan.BidInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据产品标识获取产品的所有投资记录
     * @param loanId
     * @return
     */
    List<BidInfo> selectBidInfoByLoanId(Integer loanId);

    /**
     * 查询某一用户(useId对应)的时间最近的num条投资记录
     * @param paramMap
     * @return
     */
    List<BidInfo> selectBidInfoListByUidOrderByTime(Map<String, Object> paramMap);

    /**
     * 查询用户id为userId的所有投资记录
     * @param paramMap
     * @return
     */
    Long selectTotal(Map<String, Object> paramMap);

    /**
     * 分页查询用户id为userId
     * 每页pageSize条数据,第currentPage页的投资记录
     * @param paramMap
     * @return
     */
    List<BidInfo> selectBidInfoListByPage(Map<String, Object> paramMap);
}