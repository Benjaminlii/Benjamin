package com.Benjamin.p2p.service.user;

import com.Benjamin.p2p.model.user.User;
import com.Benjamin.p2p.model.vo.ResultObject;

/**
 * 用户业务逻辑
 *
 * author:Benjamin
 * date:2019.7.26
 */
public interface UserService {
    /**
     * 获取平台注册总人数
     */
    Long queryAllUserCount();

    /**
     * 根据手机号查询用户
     */
    User queryUserByPhone(String phone);

    /**
     * 用户注册
     * 包括用户的创建和账户的创建
     */
    ResultObject register(String phone, String loginPassword);

    /**
     * 根据用户id更新用户信息,只更新非空的字段
     */
    int modifyUserByUid(User updateUser);

    /**
     * 用户登录
     * @param phone 手机号作为用户名登录
     * @param loginPassword 登录密码
     * @return User对象,其中的登录时间为上次的登录时间
     */
    User login(String phone, String loginPassword);
}
