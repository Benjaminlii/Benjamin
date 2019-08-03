package com.Benjamin.p2p.mapper.loan;

import com.Benjamin.p2p.model.loan.IncomeRecord;

import java.util.List;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);

    /**
     * 根据收益记录的状态查询收益记录列表
     * @param incomeStatue
     * @return
     */
    List<IncomeRecord> selectIncomeRecordByIncomeStatus(Integer incomeStatue);
}