package com.Benjamin.p2p.mapper.user;

import com.Benjamin.p2p.model.user.FinanceAccount;

import java.util.List;
import java.util.Map;

public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);

    /**
     * 根据user的id查询账户信息
     * @param uid
     * @return
     */
    FinanceAccount selectFinanceAccountByUid(Integer uid);

    /**
     * 用户投资时更新账户可用余额
     * @param paraMap
     * @return
     */
    int updateAvailableMoneyByUid(Map<String, Object> paraMap);

    /**
     * 收益结算时更新账户余额
     * @param paramMap
     * @return
     */
    Integer updateFinanceAccountByIncomeBack(Map<String, Object> paramMap);
}