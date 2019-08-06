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

    /**
     * 添加充值记录
     * @param rechargeRecord
     * @return
     */
    Integer addRechargeRecord(RechargeRecord rechargeRecord);

    /**
     * 根据唯一的充值订单号更新充值记录
     * @param updateRechargeRecord
     * @return
     */
    Integer modifyRechargeRecordByRechargeNo(RechargeRecord updateRechargeRecord);

    /**
     * 给用户充值[更新账户可用余额,更新充值记录状态为1]
     * @param paramMap
     * @return
     */
    Integer recharge(Map<String, Object> paramMap);
}
