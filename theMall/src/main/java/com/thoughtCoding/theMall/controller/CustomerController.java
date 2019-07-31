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
                                           @RequestParam(value = "image", required = true) File image){
        Map<String, String> retMap = new HashMap<>();

        Boolean isOk = customerService.AddCustomer(customerName, customerSex, customerAge, phone, image);
        if(isOk){
            retMap.put(Constants.ERROR_MESSAGE, Constants.OK);
        }else{
            retMap.put(Constants.ERROR_MESSAGE, "服务器繁忙,请稍后再试.");
        }

        return retMap;
    }
}
