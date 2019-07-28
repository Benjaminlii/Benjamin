package com.Benjamin.p2p.service.user;

import com.Benjamin.p2p.mapper.user.FinanceAccountMapper;
import com.Benjamin.p2p.model.user.FinanceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户业务逻辑
 * <p>
 * author:Benjamin
 * date:2019.7.28
 */
@Service
public class FinanceAccountServiceImpl implements FinanceAccountService {

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Override
    public FinanceAccount queryFinanceAccountByUid(Integer uid) {
        return financeAccountMapper.selectFinanceAccountByUid(uid);
    }
}
