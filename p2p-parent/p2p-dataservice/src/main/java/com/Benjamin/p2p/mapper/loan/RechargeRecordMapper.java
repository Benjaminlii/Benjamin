package com.Benjamin.p2p.mapper.loan;

import com.Benjamin.p2p.model.loan.RechargeRecord;

import java.util.List;
import java.util.Map;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);

    /**
     * 查询某用户近期充值记录
     * @param paramMap
     * @return
     */
    List<RechargeRecord> selectRechargeByUidOrderByTime(Map<String, Object> paramMap);

    /**
     * 查询某用户的充值记录数
     * @param userId
     * @return
     */
    Long selectTotalByUid(Integer userId);

    /**
     * 分页查询查询uid为userId
     * 每页pageSize条数据,第currentPage页的数据
     * @param paramMap
     * @return
     */
    List<RechargeRecord> selectRechargeByPage(Map<String, Object> paramMap);

    /**
     * 根据充值订单编号更新订单信息
     * @param updateRechargeRecord
     * @return
     */
    Integer updateRechargeRecordByRechargeRecordNo(RechargeRecord updateRechargeRecord);
}