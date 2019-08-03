package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.mapper.loan.BidInfoMapper;
import com.Benjamin.p2p.mapper.loan.LoanInfoMapper;
import com.Benjamin.p2p.mapper.user.FinanceAccountMapper;
import com.Benjamin.p2p.model.loan.BidInfo;
import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.model.vo.BidUserTop;
import com.Benjamin.p2p.model.vo.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;
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
    private LoanInfoMapper loanInfoMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Double queryAllBidMoney() {

        //获取指定的key的操作对象
        BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps(Constants.ALL_BID_MONEY);

        //获取key对应的value
        Double allBidMoney = (Double) boundValueOps.get();

        //判断是否有值
        if (allBidMoney == null) {
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

    @Override
    public ResultObject invest(Map<String, Object> paraMap) {
        ResultObject resultObject = new ResultObject();
        resultObject.setErrorCode(Constants.SUCCESS);

        //使用数据库乐观锁防止超卖现象
        //更新之前先查询数据的版本,更新时进行比对,如果版本不一致说明数据发生了改动,可能会发生超卖

        //获取产品的版本号
        LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey((Integer) paraMap.get("loanId"));
        paraMap.put("version", loanInfo.getVersion());

        //更新产品可投金额
        int updateLeftProductMoneyCount = loanInfoMapper.updateLeftProductMoneyByLoanId(paraMap);

        //产品可投金额是否更新成功
        if (updateLeftProductMoneyCount > 0) {
            //更新账户可用余额
            int updateAvailableMoneyCount = financeAccountMapper.updateAvailableMoneyByUid(paraMap);
            //账户余额是否更新成功
            if (updateAvailableMoneyCount > 0) {
                //新增投资记录
                BidInfo bidInfo = new BidInfo();
                bidInfo.setUid((Integer) paraMap.get("userId"));
                bidInfo.setLoanId((Integer) paraMap.get("loanId"));
                bidInfo.setBidMoney((Double) paraMap.get("bidMoney"));
                bidInfo.setBidStatus(1);
                bidInfo.setBidTime(new Date());

                int insertBidCount = bidInfoMapper.insertSelective(bidInfo);

                if (insertBidCount > 0) {
                    //查看产品剩余可投金额,如果为0更新其状态
                    LoanInfo loanDetail = loanInfoMapper.selectByPrimaryKey((Integer) paraMap.get("loanId"));

                    if (loanDetail.getLeftProductMoney() == 0) {
                        //修改为满标状态,设置满标时间
                        LoanInfo updateLoanInfo = new LoanInfo();
                        updateLoanInfo.setId((Integer) paraMap.get("loanId"));
                        updateLoanInfo.setProductStatus(1);
                        updateLoanInfo.setProductFullTime(new Date());

                        int updateCount = loanInfoMapper.updateByPrimaryKeySelective(updateLoanInfo);

                        if (updateCount < 0) {
                            resultObject.setErrorCode(Constants.FAIL);
                        }
                    }
                }

                //将用户的投资金额存放到redis中,使用zset存放
                redisTemplate.opsForZSet().incrementScore(
                        Constants.INVEST_TOP,
                        (String) paraMap.get("phone"),
                        (Double) paraMap.get("bidMoney")
                );

            } else {
                resultObject.setErrorCode(Constants.FAIL);
            }
        } else {
            resultObject.setErrorCode(Constants.FAIL);
        }

        //以上产生了fail的操作需要进行回滚
        if (Constants.FAIL.equals(resultObject.getErrorCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
        }
        return resultObject;
    }

    @Override
    public List<BidUserTop> queryBidUserTop() {
        List<BidUserTop> bidUserTops = new ArrayList<>();

        //取出Redis中key为Constants.INVEST_TOP,按倒序排序后,的第0~9个value
        Set<ZSetOperations.TypedTuple<Object>> typedTuples =
                redisTemplate.opsForZSet().reverseRangeWithScores(Constants.INVEST_TOP, 0, 9);

        //使用迭代器迭代
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTuples.iterator();
        while (iterator.hasNext()){
            //取出迭代器中当前对象
            ZSetOperations.TypedTuple<Object> next = iterator.next();
            //取出手机号 score
            String phone = (String) next.getValue();
            Double score = next.getScore();

            //封装数据
            BidUserTop bidUserTop = new BidUserTop();
            bidUserTop.setPhone(phone);
            bidUserTop.setScore(score);

            bidUserTops.add(bidUserTop);
        }

        return bidUserTops;
    }
}






