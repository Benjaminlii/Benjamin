package com.thoughtCoding.alarm.service;

import com.thoughtCoding.alarm.model.User;

/**
 * ClassName:UserService
 * Package:com.thoughtCoding.alarm.service
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午5:27
 */
public interface UserService {
    /**
     * 通过手机号和密码查询用户
     * 用于登录校验
     * @param phone 手机号
     * @param password 密码
     * @return 查询到返回对象,否则返回null
     */
    User queryUserByPhoneAndPassword(String phone, String password);

    /**
     * 注册用户,需要判断phone已经被注册过
     * @param phone 注册使用的手机号
     * @param password 设置的密码
     * @return 注册是否成功
     */
    boolean addUser(String phone, String password);
}
