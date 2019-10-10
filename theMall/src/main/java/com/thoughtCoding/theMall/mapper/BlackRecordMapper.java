package com.thoughtCoding.theMall.mapper;

import com.thoughtCoding.theMall.model.BlackRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BlackRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_black_record
     *
     * @mbggenerated Tue Sep 17 15:55:28 CST 2019
     */
    int deleteByPrimaryKey(Integer recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_black_record
     *
     * @mbggenerated Tue Sep 17 15:55:28 CST 2019
     */
    int insert(BlackRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_black_record
     *
     * @mbggenerated Tue Sep 17 15:55:28 CST 2019
     */
    int insertSelective(BlackRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_black_record
     *
     * @mbggenerated Tue Sep 17 15:55:28 CST 2019
     */
    BlackRecord selectByPrimaryKey(Integer recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_black_record
     *
     * @mbggenerated Tue Sep 17 15:55:28 CST 2019
     */
    int updateByPrimaryKeySelective(BlackRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_black_record
     *
     * @mbggenerated Tue Sep 17 15:55:28 CST 2019
     */
    int updateByPrimaryKey(BlackRecord record);

    /**
     * 通过时间戳查询当天的所有黑名单出现记录
     * @param paraMap
     * @return 记录list,其中添加customer属性为customer对象
     */
    List<BlackRecord> selectBlackRecordWithCustomerByTimestamp(Map<String, String> paraMap);
}