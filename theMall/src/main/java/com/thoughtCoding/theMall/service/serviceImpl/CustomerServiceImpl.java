package com.thoughtCoding.theMall.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtCoding.theMall.mapper.CustomerMapper;
import com.thoughtCoding.theMall.model.Customer;
import com.thoughtCoding.theMall.service.CustomerService;
import com.thoughtCoding.theMall.utils.HttpClientUtils;
import com.thoughtCoding.theMall.utils.MD5Util;
import com.thoughtCoding.theMall.utils.SignUtil;
import com.thoughtCoding.theMall.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * author:Benjamin
 * date:2019.7.31
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Boolean AddCustomer(String customerName, Byte customerSex, Byte customerAge, String phone, File image) {

        //想库中添加新的顾客信息
        //封装信息
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerAge(customerAge);
        customer.setCustomerSex(customerSex);
        customer.setPhone(phone);
        customer.setCustomerLastTime(new Date());
        customer.setCustomerIsBlack((byte) 0);
        customer.setCustomerType((byte) 2);

        //插入到数据库中
        int insertCount = customerMapper.insertSelective(customer);
        if (insertCount == 0) {
            return false;
        }
        customer = customerMapper.selectCustomerByPhone(phone);
        //插入成功,向人脸库中录入人脸
        //设置请求信息,这里TreeMap会自动升序排序
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("access_key", Constants.ACCESS_KEY);
        paramMap.put("company_id", Constants.COMPANY_ID);
        paramMap.put(Constants.ACTION, "add");
        paramMap.put("gender", customerSex);
        paramMap.put("name", customerName);
        paramMap.put("num", customer.getCustomerId());
        paramMap.put("timestamp", new Date().getTime());

        //安全签名算法
        String sign = SignUtil.getSign(paramMap);
        paramMap.put(Constants.SIGN, sign);

        paramMap.put("image", image);
        //发送请求
        String result = HttpClientUtils.doPost(Constants.SHENMU_URL, paramMap);
//        String result = "{code:1000}";
        JSONObject jsonObject = JSONObject.parseObject(result);

        if(jsonObject.getInteger("code") != 1000){
            //请求失败
            return false;
        }

        return true;
    }
}
