package com.thoughtCoding.theMall.service.serviceImpl;

import com.thoughtCoding.theMall.mapper.UserMapper;
import com.thoughtCoding.theMall.model.User;
import com.thoughtCoding.theMall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:Benjamin
 * date:2019.7.30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return userMapper.selectUserByUsernameAndPassword(username, password);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public boolean register(String username, String password, Byte shopId, Byte userType) {
        //构造新的user
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setShopId(shopId);
        user.setUserType(userType);

        //插入到use表中
        int insertCount = userMapper.insertSelective(user);

        return insertCount > 0;
    }

    @Override
    public boolean modifyUserByUid(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }
}
