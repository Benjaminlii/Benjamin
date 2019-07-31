package com.thoughtCoding.theMall.service;

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
     * 得到(某类型物品)近一年按年龄划分的出货量
     */
    Map<String, Integer> queryCountByAge(String recordType);
}
