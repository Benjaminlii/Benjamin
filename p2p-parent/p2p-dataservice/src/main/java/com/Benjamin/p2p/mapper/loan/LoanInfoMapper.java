package com.Benjamin.p2p.mapper.loan;

import com.Benjamin.p2p.model.loan.LoanInfo;

import java.util.List;
import java.util.Map;

public interface LoanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);

    LoanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanInfo record);

    int updateByPrimaryKeyWithBLOBs(LoanInfo record);

    int updateByPrimaryKey(LoanInfo record);

    /**
     * 历史平均年化收益率
     */
    Double selectHistoryAverageRate();

    /**
     * 分页查询产品信息列表
     */
    List<LoanInfo> selectLoanInfoByPage(Map<String, Object> paramMap);

    /**
     * 根据类型获取产品总数
     * @param paramMap
     * @return
     */
    Long selectTotal(Map<String, Object> paramMap);

    /**
     * 更新产品剩余可投金额
     * @param paraMap
     * @return
     */
    int updateLeftProductMoneyByLoanId(Map<String, Object> paraMap);
}