package com.Benjamin.p2p.service.user;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.mapper.user.FinanceAccountMapper;
import com.Benjamin.p2p.mapper.user.UserMapper;
import com.Benjamin.p2p.model.user.FinanceAccount;
import com.Benjamin.p2p.model.user.User;
import com.Benjamin.p2p.model.vo.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务逻辑
 * <p>
 * author:Benjamin
 * date:2019.7.26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Long queryAllUserCount() {
        //先去Redis缓存中获取,有则直接使用,没有去数据库中查询,并存放到Redis缓存中.

        //首先获取指定操作某一个key的操作对象
        BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps(Constants.ALL_USER_COUNT);

        //获取这个key的value
        Long allUserCount = (Long) boundValueOps.get();
        //判断是否有值
        if (allUserCount == null) {
            //没有值,去数据库查询
            allUserCount = userMapper.selectAllUserCount();

            //将查询出的数据放入Redis缓存
            boundValueOps.set(allUserCount, 15, TimeUnit.MINUTES);
        }
        return allUserCount;
    }

    @Override
    public User queryUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public ResultObject register(String phone, String loginPassword) {
        ResultObject resultObject = new ResultObject();
        resultObject.setErrorCode(Constants.SUCCESS);

        //新增用户
        User user = new User();
        user.setPhone(phone);
        user.setLoginPassword(loginPassword);
        user.setAddTime(new Date());
        user.setLastLoginTime(new Date());
        int insertUserCount = userMapper.insertSelective(user);

        if (insertUserCount > 0) {
            //查询刚刚创建的user
            user = userMapper.selectUserByPhone(phone);
            //新增账户
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(user.getId());
            financeAccount.setAvailableMoney(Constants.INIT_MONEY);
            int insertFinanceCount = financeAccountMapper.insertSelective(financeAccount);
            if(insertFinanceCount < 0){
                resultObject.setErrorCode(Constants.FAIL);
            }
        }else{
            resultObject.setErrorCode(Constants.FAIL);
        }

        return resultObject;
    }

    @Override
    public int modifyUserByUid(User updateUser) {
        return userMapper.updateByPrimaryKeySelective(updateUser);
    }
}
