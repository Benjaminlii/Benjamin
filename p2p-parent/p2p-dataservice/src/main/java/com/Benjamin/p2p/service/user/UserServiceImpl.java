package com.Benjamin.p2p.service.user;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户业务逻辑
 *
 * author:Benjamin
 * date:2019.7.26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
}
