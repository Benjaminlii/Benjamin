package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.common.constant.Constans;
import com.Benjamin.p2p.mapper.loan.LoanInfoMapper;
import com.Benjamin.p2p.service.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * author：Benjamin
 * date：2019.7.25
 */
@Service
public class LoanInfoServiceImpl implements LoanInfoService {

    @Autowired
    private LoanInfoMapper loanInfoMapper;

    //Redis模板对象
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public double queryHistoryAverageRate() {
        //先去Redis缓存中获取,有则直接使用,没有去数据库中查询,并存放到Redis缓存中.

        //首先获取操作key-value数据类型的Redis操作对象
        Double historyAverageRate = (Double) redisTemplate.opsForValue().get(Constans.HISTORY_AVERAGE_RATE);

        //判断是否有值
        if (historyAverageRate == null) {
            //没有值,去数据库查询
            historyAverageRate = loanInfoMapper.selectHistoryAverageRate();

            //将查询出的数据放入Redis缓存
            redisTemplate.opsForValue().set(Constans.HISTORY_AVERAGE_RATE, historyAverageRate, 15, TimeUnit.MINUTES);
        }
        return historyAverageRate;
    }
}
