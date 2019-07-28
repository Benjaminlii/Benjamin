package com.Benjamin.p2p.mapper.user;

import com.Benjamin.p2p.model.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 获取平台注册总人数
     */
    Long selectAllUserCount();

    /**
     * 根据手机号查询用户
     */
    User selectUserByPhone(String phone);

    /**
     * 查询同事匹配手机号和登录密码的用户
     */
    User selectUserByPhoneAndLoginPassword(String phone, String loginPassword);
}