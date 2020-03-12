package com.thoughtCoding.theMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtCoding.theMall.model.User;

/**
 * ClassName:TestController
 * Package:com.thoughtCoding.theMall.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-12 下午6:30
 */
@Controller
public class TestController {
    @RequestMapping(value = "/testFilter")
    @ResponseBody
    public Object testFilter(@RequestAttribute(value = "user", required = false)User user){
        System.out.println(user);
        User u = new User();
        u.setUsername("benjaminLee");
        return user;
    }
}
