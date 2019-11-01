package com.thoughtCoding.theMall.service.serviceImpl;

import com.thoughtCoding.theMall.mapper.BlackRecordMapper;
import com.thoughtCoding.theMall.model.BlackRecord;
import com.thoughtCoding.theMall.model.Customer;
import com.thoughtCoding.theMall.service.BlackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("year", timeStamp.getYear()+"");
        paraMap.put("month", (timeStamp.getMonth()+1)+"");
        paraMap.put("day", timeStamp.getDate()+"");
        return blackRecordMapper.selectBlackRecordWithCustomerByTimestamp(paraMap);
    }

    @Override
    public Boolean addBlackRecord(Map<String, Object> paraMap) {
        BlackRecord blackRecord = new BlackRecord();
        blackRecord.setCustomerId(((Customer) paraMap.get("customer")).getCustomerId());
        blackRecord.setFaceUrl((String) paraMap.get("face_url"));
        blackRecord.setFrameUrl((String) paraMap.get("frame_url"));
        blackRecord.setTimestamp(new Date((Long) paraMap.get("timestamp")));

        return blackRecordMapper.insertSelective(blackRecord) > 0;
    }
}
