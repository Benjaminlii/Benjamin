package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.common.util.DateUtils;
import com.Benjamin.p2p.mapper.loan.BidInfoMapper;
import com.Benjamin.p2p.mapper.loan.IncomeRecordMapper;
import com.Benjamin.p2p.mapper.loan.LoanInfoMapper;
import com.Benjamin.p2p.mapper.user.FinanceAccountMapper;
import com.Benjamin.p2p.model.loan.BidInfo;
import com.Benjamin.p2p.model.loan.IncomeRecord;
import com.Benjamin.p2p.model.loan.LoanInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncomeRecordServiceImpl implements IncomeRecordService {

    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Autowired
    private IncomeRecordMapper incomeRecordMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    private Logger logger = LogManager.getLogger(IncomeRecordServiceImpl.class);

    @Override
    public void generateIncomePlan() {
        //查询产品状态为1,即已满标产品 ---> 返回list<loanInfo>
        List<LoanInfo> loanInfoList = loanInfoMapper.selectLoanInfoByProductStatus(1);

        //循环遍历,获取每一个满标产品
        for (LoanInfo loanInfo : loanInfoList) {
            //查询对应的所有投资记录 ---> 返回list<FinanceAccount>
            List<BidInfo> bidInfoList = bidInfoMapper.selectBidInfoByLoanId(loanInfo.getId());
            //循环遍历,获取每一条投资记录
            for (BidInfo bidInfo : bidInfoList) {
                //生成对应的收益记录
                IncomeRecord incomeRecord = new IncomeRecord();
                incomeRecord.setUid(bidInfo.getUid());
                incomeRecord.setLoanId(bidInfo.getLoanId());
                incomeRecord.setBidId(bidInfo.getId());
                incomeRecord.setBidMoney(bidInfo.getBidMoney());
                //收益状态,0为未返还,1为已返还
                incomeRecord.setIncomeStatus(0);

                //收益时间为产品满标时间+产品周期(根据产品类型分为按天为周期和按月为周期)
                //收益金额为投资金额*日利率*周期
                Date incomeRecordTime = null;
                Double incomeMoney = null;
                if (Constants.PRODUCT_TYPE_X.equals(loanInfo.getProductType())) {
                    //新手宝产品,周期单位为天
                    incomeRecordTime = DateUtils.getDateByAddDays(loanInfo.getProductFullTime(), loanInfo.getCycle());
                    //收益
                    incomeMoney = bidInfo.getBidMoney() * (loanInfo.getRate() / 100 / 365) * loanInfo.getCycle();
                } else {
                    //优选和散标产品,周期为月
                    incomeRecordTime = DateUtils.getDateByAddMonthes(loanInfo.getProductFullTime(), loanInfo.getCycle());
                    //收益
                    incomeMoney = bidInfo.getBidMoney() * (loanInfo.getRate() / 100 / 365) * loanInfo.getCycle() * 30;
                }
                //保留小数点后两位小数
                incomeMoney = Math.round(incomeMoney * Math.pow(10, 2)) / Math.pow(10, 2);

                incomeRecord.setIncomeDate(incomeRecordTime);
                incomeRecord.setIncomeMoney(incomeMoney);

                int insertCount = incomeRecordMapper.insertSelective(incomeRecord);

                if (insertCount > 0) {
                    logger.info("用户标识为:" + bidInfo.getUid() + "投资记录标识:" + bidInfo.getId() + ",生成受益计划成功.");
                } else {
                    logger.info("用户标识为:" + bidInfo.getUid() + "投资记录标识:" + bidInfo.getId() + ",生成受益计划失败.");
                }
            }
            //讲当前产品状态更新为2
            LoanInfo updateLoanInfo = new LoanInfo();
            updateLoanInfo.setId(loanInfo.getId());
            updateLoanInfo.setProductStatus(2);
            int updateCount = loanInfoMapper.updateByPrimaryKeySelective(updateLoanInfo);

            if (updateCount > 0) {
                logger.info("产品标识为:" + loanInfo.getId() + ",修改状态为满标且生成受益计划成功.");
            } else {
                logger.info("产品标识为:" + loanInfo.getId() + ",修改状态为满标且生成受益计划失败.");
                //这里没有逻辑上的错误,不用手动回滚
            }

        }
    }

    @Override
    public void generateIncomeBack() {
        //查询收益装填为0,且收益时间与当前时间相同的收益计划 -->list<IncomeRecord>
        List<IncomeRecord> incomeRecordList = incomeRecordMapper.selectIncomeRecordByIncomeStatus(0);

        //遍历收益记录列表,得到每一个应该返还的收益记录
        for (IncomeRecord incomeRecord : incomeRecordList) {
            //更新用户账户余额
            //准备参数,uid,投资本金bidMoney,账户新增余额incomeMoney
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", incomeRecord.getUid());
            paramMap.put("bidMoney", incomeRecord.getBidMoney());
            paramMap.put("incomeMoney", incomeRecord.getIncomeMoney());

            Integer updateFinanceAccountCount = financeAccountMapper.updateFinanceAccountByIncomeBack(paramMap);
            if (updateFinanceAccountCount > 0) {
                //将本条收益记录状态更改为1已返还
                IncomeRecord updateIncome = new IncomeRecord();
                updateIncome.setId(incomeRecord.getId());
                updateIncome.setIncomeStatus(1);

                Integer updateIncomeCount = incomeRecordMapper.updateByPrimaryKeySelective(updateIncome);

                if(updateIncomeCount > 0){
                    logger.info("用户标识为:" + incomeRecord.getUid() + ",收益记录标识为：" + incomeRecord.getId() + ",收益记录返还成功");
                }else{
                    logger.info("用户标识为:" + incomeRecord.getUid() + ",收益记录标识为：" + incomeRecord.getId() + ",收益记录返还失败");
                }

            } else {
                logger.info("用户标识为:" + incomeRecord.getUid() + ",收益记录标识为：" + incomeRecord.getId() + ",收益记录返还失败");
            }
        }


    }
}
