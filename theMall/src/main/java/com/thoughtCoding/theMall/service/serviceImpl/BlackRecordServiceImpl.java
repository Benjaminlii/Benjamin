package com.thoughtCoding.theMall.service.serviceImpl;

import com.thoughtCoding.theMall.mapper.BlackRecordMapper;
import com.thoughtCoding.theMall.model.BlackRecord;
import com.thoughtCoding.theMall.service.BlackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ClassName:BlackRecordServiceImpl
 * Package:com.thoughtCoding.theMall.service.serviceImpl
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-9-17 下午4:01
 */
@Service
public class BlackRecordServiceImpl implements BlackRecordService {
    @Autowired
    public BlackRecordMapper blackRecordMapper;

    @Override
    public List<BlackRecord> queryBlackRecordByTimestamp(Date timeStamp) {
        return blackRecordMapper.selectBlackRecordWithCustomerByTimestamp(timeStamp);
    }
}
