package com.Benjamin.p2p.mapper.loan;

import com.Benjamin.p2p.model.loan.IncomeRecord;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询某用户近期投资记录
     * @param paramMap
     * @return
     */
    List<IncomeRecord> selectIncomeByUidOrderByTime(Map<String, Object> paramMap);

    /**
     * 查询uid为userId的收益记录
     * @param userId
     * @return
     */
    Long selectTotalByUid(Integer userId);

    /**
     * 分页查询uid为userId
     * 每页pageSize条数据,第currentPage页的收益记录
     * @param paramMap
     * @return
     */
    List<IncomeRecord> selectIncomeByPage(Map<String, Object> paramMap);
}