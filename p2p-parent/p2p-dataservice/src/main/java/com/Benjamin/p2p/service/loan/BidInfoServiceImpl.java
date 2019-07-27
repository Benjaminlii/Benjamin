package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.mapper.loan.BidInfoMapper;
import com.Benjamin.p2p.model.loan.BidInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 投资记录业务逻辑
 * <p>
 * author:Benjamin
 * date:2019.7.26
 */
@Service
public class BidInfoServiceImpl implements BidInfoService {

    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Double queryAllBidMoney() {

        //获取指定的key的操作对象
        BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps(Constants.ALL_BID_MONEY);

        //获取key对应的value
        Double allBidMoney = (Double) boundValueOps.get();

        //判断是否有值
        if(allBidMoney == null){
            //Redis中没有,去数据库中查找
            allBidMoney = bidInfoMapper.selectAllBidMoney();

            //存入Redis
            boundValueOps.set(allBidMoney, 15, TimeUnit.MINUTES);
        }

        return allBidMoney;
    }

    @Override
    public List<BidInfo> queryBidListByLoanId(Integer loanId) {
        return bidInfoMapper.selectBidInfoListByLoanId(loanId);
    }
}
