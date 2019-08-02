package com.thoughtCoding.theMall.service;

import com.thoughtCoding.theMall.model.Customer;

import java.io.File;

/**
 * author:Benjamin
 * date:2019.7.31
 */
public interface CustomerService {

    /**
     * 添加顾客信息,目前都为vip
     * 向人脸库中添加信息
     */
    Boolean AddCustomer(String customerName, Byte customerSex, Byte customerAge, String phone, File image);

    /**
     * 捕捉到人脸
     * 对其进行判断,并更新时间,统计购买数据
     * 判断该顾客是否存在,如果存在判断上次到店时间,更新?
     */
    Customer captureCustomer(Integer customerId, String customerName);
}
