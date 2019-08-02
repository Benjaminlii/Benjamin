package com.thoughtCoding.theMall.service;

import com.thoughtCoding.theMall.model.Record;

import java.util.List;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.7.30
 */
public interface RecordService {
    /**
     * 得到(某类型物品)某一年按月份统计的出货量
     */
    Map<String, Integer> queryCountByMonth(String recordType, int year);

    /**
     * 得到(某类型物品)某一月按日统计的出货量
     */
    Map<String, Integer> queryCountByDay(String recordType, int year, int month);

    /**
     * 得到(某类型物品)某一月按周统计的出货量
     */
    Map<String, Integer> queryCountByWeek(String recordType, Integer year, Integer month);

    /**
     * 得到(某类型物品)近一年按年龄划分的出货量
     */
    Map<String, Integer> queryCountByAge(String recordType);

    /**
     * 根据顾客id查询历史购买记录
     */
    List<Record> queryRecordsByCustomerId(Integer customerId);

    /**
     * 查询对应id顾客历史购买记录中出现最多的种类
     */
    String queryFavoriteByCustomerId(Integer customerId);

    /**
     * 查询所有的商品类型
     */
    List<String> queryAllRecordTypes();
}
