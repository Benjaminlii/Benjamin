package com.thoughtCoding.theMall.service.serviceImpl;

import com.thoughtCoding.theMall.mapper.RecordMapper;
import com.thoughtCoding.theMall.model.Record;
import com.thoughtCoding.theMall.service.RecordService;
import com.thoughtCoding.theMall.vo.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author:Benjamin
 * date:2019.7.30
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Map<String, Integer> queryCountByMonth(String recordType, int year) {
        Map<String, Integer> rtn = new HashMap<>();

        //封装查询信息
        Map<String, String> selectMap = new HashMap<>();
        selectMap.put("recordType", recordType);
        selectMap.put("year", year + "");
        //查询list
        List<Statistics> statistics = recordMapper.selectCountByMonth(selectMap);

        //将list中的信息转存入map中
        for (Statistics statistic : statistics) {
            rtn.put("m" + statistic.getTime(), statistic.getSum());
        }

        //将未统计的月份标为零
        Set<String> set = rtn.keySet();
        for (int i = 1; i <= 12; i++) {
            if (!set.contains("m" + i)) {
                rtn.put("m" + i, 0);
            }
        }

        return rtn;
    }

    @Override
    public Map<String, Integer> queryCountByDay(String recordType, int year, int month) {
        Map<String, Integer> rtn = new HashMap<>();

        //封装查询信息
        Map<String, String> selectMap = new HashMap<>();
        selectMap.put("recordType", recordType);
        selectMap.put("year", year + "");
        selectMap.put("month", month + "");

        //查询list
        List<Statistics> statistics = recordMapper.selectCountByDay(selectMap);

        //将list中的信息转存至map中
        for (Statistics statistic : statistics) {
            rtn.put("day" + statistic.getTime(), statistic.getSum());
        }

        //获取这个月的天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(year, month, 1));
        int numOfDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //将未统计的月份标为零
        Set<String> set = rtn.keySet();
        for (int i = 1; i <= numOfDay; i++) {
            if (!set.contains("day" + i)) {
                rtn.put("day" + i, 0);
            }
        }

        return rtn;
    }

    @Override
    public Map<String, Integer> queryCountByAge(String recordType) {
        Map<String, Integer> rtn = new HashMap<>();

        //进行统计
        List<Statistics> statistics = recordMapper.selectCountByAge(recordType);

        //信息转存到map中
        for (Statistics statistic : statistics) {
            rtn.put(statistic.getTime(), statistic.getSum());
        }

        //填充0
        Set<String> set = rtn.keySet();
        for (int i = 10; i <= 70; i += 10) {
            if (!set.contains(i + "")) {
                rtn.put(i + "", 0);
            }
        }

        return rtn;
    }

    @Override
    public List<Record> queryRecordsByCustomerId(Integer customerId) {
        return recordMapper.selectRecordsByCustomerId(customerId);
    }
}
