package com.Benjamin.p2p.timer;

import com.Benjamin.p2p.service.loan.IncomeRecordService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时调度测试
 *
 * author:Benjamin
 * date:2019.8.3
 */
@Component
public class TimerManager {

    private Logger logger = LogManager.getLogger(TimerManager.class);

    @Autowired
    private IncomeRecordService incomeRecordService;

    /**
     * @Scheduled 内cron表达式限定方法执行的时间
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void generateIncomePlan(){

        logger.info("---------------生成受益计划开始--------------");

        incomeRecordService.generateIncomePlan();


        logger.info("---------------生成受益计划结束--------------");

    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void generateIncomeBack(){

        logger.info("---------------生成受益计划开始--------------");

        incomeRecordService.generateIncomeBack();


        logger.info("---------------生成受益计划结束--------------");

    }
}
