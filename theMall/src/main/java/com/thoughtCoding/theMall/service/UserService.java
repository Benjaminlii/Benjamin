package com.thoughtCoding.theMall.service;

import com.thoughtCoding.theMall.model.User;

/**
 * author:Benjamin
 * date:2019.7.30
 */
public interface UserService {

    /**
     * 通过用户名和密码查询user信息
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 根据username查询user
     */
    User queryUserByUsername(String username);

    /**
     * 用户注册
     */
    boolean register(String username, String password, Byte shopId, Byte userType);

    /**
     * 根据uid更新user信息(只更新已填充部分)
     */
    boolean modifyUserByUid(User user);
}
