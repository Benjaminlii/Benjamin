package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.model.vo.PaginatinoVo;

import java.util.List;
import java.util.Map;

/**
 * 产品业务逻辑
 *
 * author:Benjamin
 * date:2019.7.25
 */
public interface LoanInfoService {

    /**
     * 获取平台历史平均年化收益率
     */
    double queryHistoryAverageRate();

    /**
     * 根据产品类型获取产品列表
     * @param paramMap
     * @return
     */
    List<LoanInfo> queryLoanInfoByProductType(Map<String, Object> paramMap);

    /**
     * 分页查询产品信息列表
     * @param paramMap
     * @return
     */
    PaginatinoVo<LoanInfo> queryLoanInfoByPage(Map<String, Object> paramMap);

    /**
     * 根据产品id查询产品信息
     * @param id
     * @return
     */
    LoanInfo queryLoanInfoById(Integer id);
}
