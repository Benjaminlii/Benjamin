package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.model.loan.RechargeRecord;
import com.Benjamin.p2p.model.vo.PaginatinoVo;

import java.util.List;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.8.4
 */
public interface RechargeRecordService {

    /**
     * 查询某用户最近的充值记录
     * @param userId
     * @return
     */
    List<RechargeRecord> queryRechargeByUidOrderByTime(Integer userId);

    /**
     * 分页查询用户userId
     * 每页pageSize条数据,第currentPage页数据
     * @param paramMap
     * @return
     */
    PaginatinoVo<RechargeRecord> queryRechargeByPage(Map<String, Object> paramMap);
}
