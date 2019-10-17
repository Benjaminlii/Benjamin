package com.thoughtCoding.alarm.service;

import com.thoughtCoding.alarm.mapper.UserMapper;
import com.thoughtCoding.alarm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserServiceImpl
 * Package:com.thoughtCoding.alarm.service
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午5:28
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByPhoneAndPassword(String phone, String password) {

        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);

        return userMapper.findByPhoneAndPassword(user);
    }

    @Override
    public boolean addUser(String phone, String password) {
        if (userMapper.findByPhone(phone)==null){
            //手机号未被注册
            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            return userMapper.insertSelective(user) > 0;

        }

        return false;
    }
}
