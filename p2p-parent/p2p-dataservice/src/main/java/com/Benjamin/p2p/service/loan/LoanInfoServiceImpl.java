package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.mapper.loan.LoanInfoMapper;
import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.model.vo.PaginatinoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 产品业务逻辑
 *
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
        Double historyAverageRate = (Double) redisTemplate.opsForValue().get(Constants.HISTORY_AVERAGE_RATE);

        //判断是否有值
        if (historyAverageRate == null) {
            //没有值,去数据库查询
            historyAverageRate = loanInfoMapper.selectHistoryAverageRate();

            //将查询出的数据放入Redis缓存
            redisTemplate.opsForValue().set(Constants.HISTORY_AVERAGE_RATE, historyAverageRate, 15, TimeUnit.MINUTES);
        }
        return historyAverageRate;
    }

    @Override
    public List<LoanInfo> queryLoanInfoByProductType(Map<String, Object> paramMap) {
        return loanInfoMapper.selectLoanInfoByPage(paramMap);
    }

    @Override
    public PaginatinoVo<LoanInfo> queryLoanInfoByPage(Map<String, Object> paramMap) {
        PaginatinoVo<LoanInfo> paginatinoVo = new PaginatinoVo<>();

        //根据产品类型获取产品总数
        Long total = loanInfoMapper.selectTotal(paramMap);

        //分页查询产品信息
        List<LoanInfo> loanInfos = loanInfoMapper.selectLoanInfoByPage(paramMap);

        //总记录数
        paginatinoVo.setTotal(total);
        //数据
        paginatinoVo.setDataList(loanInfos);

        return paginatinoVo;
    }

    @Override
    public LoanInfo queryLoanInfoById(Integer id) {
        return loanInfoMapper.selectByPrimaryKey(id);
    }
}
