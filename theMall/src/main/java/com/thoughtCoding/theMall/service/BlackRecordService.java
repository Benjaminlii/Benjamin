package com.thoughtCoding.theMall.service;

import com.thoughtCoding.theMall.model.BlackRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:BlackRecordService
 * Package:com.thoughtCoding.theMall.service
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-9-17 下午4:00
 */
public interface BlackRecordService {

    /**
     * 根据时间戳的时间查询当天的所有黑名单顾客出现记录
     * @param timeStamp
     * @return 里面的Map为BlackRecord + 对应的customer
     */
    List<BlackRecord> queryBlackRecordByTimestamp(Date timeStamp);

    /**
     * 插入黑名单的出现记录
     * @param paraMap
     * @return
     */
    Boolean addBlackRecord(Map<String, Object> paraMap);
}
