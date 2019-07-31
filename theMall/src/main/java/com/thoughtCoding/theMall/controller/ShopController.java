package com.thoughtCoding.theMall.controller;

import com.thoughtCoding.theMall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * author:Benjamin
 * date:2019.7.30
 */
@Controller
public class ShopController {

    @Autowired
    public ShopService shopService;

    /**
     * 得到店铺信息列表
     */
    @RequestMapping(value = "/getShops")
    @ResponseBody
    public Object getShops(HttpServletRequest request) {
        return shopService.queryAllShops();
    }

}
