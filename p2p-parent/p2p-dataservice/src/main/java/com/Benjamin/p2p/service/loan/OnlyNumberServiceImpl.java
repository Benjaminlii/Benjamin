package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * author:Benjamin
 * date:2019.8.6
 */
@Service
public class OnlyNumberServiceImpl implements OnlyNumberService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Long getOnlyNumber() {
        return redisTemplate.opsForValue().increment(Constants.ONLY_NUMBER, 1);
    }
}
