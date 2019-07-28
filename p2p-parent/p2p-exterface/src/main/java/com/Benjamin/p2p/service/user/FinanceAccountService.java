package com.Benjamin.p2p.service.user;

import com.Benjamin.p2p.model.user.FinanceAccount;

public interface FinanceAccountService {
    /**
     * 根据user的id查询其账户信息
     * @param uid
     * @return
     */
    FinanceAccount queryFinanceAccountByUid(Integer uid);
}
