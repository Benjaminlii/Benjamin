package com.Benjamin.p2p.mapper.user;

import com.Benjamin.p2p.model.user.FinanceAccount;

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
}