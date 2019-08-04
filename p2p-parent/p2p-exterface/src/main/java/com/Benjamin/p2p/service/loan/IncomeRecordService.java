package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.model.loan.IncomeRecord;
import com.Benjamin.p2p.model.vo.PaginatinoVo;

import java.util.List;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.8.3
 */
public interface IncomeRecordService {

    /**
     * 生成收益计划
     */
    void generateIncomePlan();

    /**
     * 收益返还
     */
    void generateIncomeBack();

    /**
     * 查询某用户近期投资记录
     * @param userId
     * @return
     */
    List<IncomeRecord> queryIncomeByUidOrderByTime(Integer userId);

    /**
     * 分页查询uid为userId
     * 每页pageSize条数据,第currentPage页的收益记录
     * @param paramMap
     * @return
     */
    PaginatinoVo<IncomeRecord> queryIncomeByPage(Map<String, Object> paramMap);
}
