package com.thoughtCoding.theMall.controller;

import com.thoughtCoding.theMall.service.CustomerService;
import com.thoughtCoding.theMall.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/addCustomer")
    @ResponseBody
    public Map<String, String> addCustomer(HttpServletRequest request,
                                           @RequestParam(value = "customerName", required = true) String customerName,
                                           @RequestParam(value = "customerSex", required = true) Byte customerSex,
                                           @RequestParam(value = "customerAge", required = true) Byte customerAge,
                                           @RequestParam(value = "phone", required = true) String phone,
                                           @RequestParam(value = "image", required = true) File image) {
        Map<String, String> retMap = new HashMap<>();

        Boolean isOk = customerService.AddCustomer(customerName, customerSex, customerAge, phone, image);
        if (isOk) {
            retMap.put(Constants.ERROR_MESSAGE, Constants.OK);
        } else {
            retMap.put(Constants.ERROR_MESSAGE, "服务器繁忙,请稍后再试.");
        }

        return retMap;
    }

    @RequestMapping(value = "/captureFace")
    @ResponseBody
    public Map<String, Object> captureFace(HttpServletRequest request,
                                           @RequestParam(value = "action ", required = true) String action,//固定值,visitor
                                           @RequestParam(value = "vip_name", required = true) String customerName,//捕捉到的顾客的姓名
                                           @RequestParam(value = "vip_num", required = true) String customerId,//顾客id
                                           @RequestParam(value = "face_url", required = true) String face_url,//人脸图片url
                                           @RequestParam(value = "frame_url", required = true) String frame_url,//帧图url,可能为空串
                                           @RequestParam(value = "timestamp", required = true) Long timestamp//捕捉到脸的时间戳
    ) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("code", 1000);

        return retMap;
    }
}
