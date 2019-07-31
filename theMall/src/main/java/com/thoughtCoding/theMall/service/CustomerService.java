package com.thoughtCoding.theMall.service;

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
}
