package com.thoughtCoding.theMall.controller;

import com.thoughtCoding.theMall.vo.Constants;
import com.thoughtCoding.theMall.model.Shop;
import com.thoughtCoding.theMall.model.User;
import com.thoughtCoding.theMall.service.ShopService;
import com.thoughtCoding.theMall.service.UserService;
import com.thoughtCoding.theMall.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.7.30
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request,
                                     @RequestParam(value = "username", required = true) String username,
                                     @RequestParam(value = "password", required = true) String password) {
        Map<String, Object> retMap = new HashMap<>();

        //按用户名和密码查询用户
        User user = userService.queryUserByUsernameAndPassword(username, MD5Util.string2MD5(password));

        //判断用户是否存在
        if (user == null) {
            //用户不存在
            retMap.put(Constants.ERROR_MESSAGE, "用户名或密码不正确,请重新输入");
            return retMap;
        }
        //用户存在
        retMap.put(Constants.ERROR_MESSAGE, Constants.OK);
        retMap.put("user", user);
        //存入session
        request.getSession().setAttribute(Constants.SESSION_USER, user);

        return retMap;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request,
                                        @RequestParam(value = "username", required = true) String username,
                                        @RequestParam(value = "password", required = true) String password,
                                        @RequestParam(value = "shopName", required = true) String shopName,
                                        @RequestParam(value = "userType", required = true) Byte userType){

        Map<String, Object>retMap = new HashMap<>();

        //查询用户名是否重复
        User user = userService.queryUserByUsername(username);
        if(user != null){
            retMap.put(Constants.ERROR_MESSAGE, "用户名已存在,请重新输入.");
            return retMap;
        }

        Byte shopId = shopService.queryShopByShopName(shopName).getShopId();

        //注册
        if(!userService.register(username, MD5Util.string2MD5(password), shopId, userType)){
            //注册失败
            retMap.put(Constants.ERROR_MESSAGE, "当前系统繁忙,请稍后重试");
            return retMap;
        }

        //重新查询用户信息
        user = userService.queryUserByUsername(username);

        //注册成功,将注册的用户信息加入session
        request.getSession().setAttribute(Constants.SESSION_USER, user);
        retMap.put("user", user);
        retMap.put(Constants.ERROR_MESSAGE, Constants.OK);

        return retMap;
    }

    @RequestMapping(value = "updateUser")
    @ResponseBody
    public Map<String , Object> updateUser(HttpServletRequest request,
                                           @RequestParam(value = "password", required = false)String password,
                                           @RequestParam(value = "userRealName", required = false)String userRealName,
                                           @RequestParam(value = "shopName", required = false)String shopName,
                                           @RequestParam(value = "userType", required = false)Byte userType){
        Map<String, Object> retMap = new HashMap<>();

        //从session获取当前登录的用户
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);

        //判断需要更新的信息
        if(password != null){
            user.setPassword(MD5Util.string2MD5(password));
        }
        if(userRealName != null){
            user.setUserRealName(userRealName);
        }
        if(shopName != null){
            Shop shop = shopService.queryShopByShopName(shopName);
            if(shop == null){
                retMap.put(Constants.ERROR_MESSAGE, "店铺名输入错误");
                return retMap;
            }
            user.setShopId(shop.getShopId());
        }
        if(userType != null){
            if(!Constants.USER_TYPE.contains(userType)){
                retMap.put(Constants.ERROR_MESSAGE, "用户类型输入错误");
                return retMap;
            }
            user.setUserType(userType);
        }

        //更新操作
        if(!userService.modifyUserByUid(user)){
            retMap.put(Constants.ERROR_MESSAGE, "系统繁忙,请稍后再试");
            return retMap;
        }

        //更新session中的user,返回新的user
        user = userService.queryUserByUsername(user.getUsername());
        request.getSession().setAttribute(Constants.SESSION_USER, user);
        retMap.put("user", user);

        retMap.put(Constants.ERROR_MESSAGE, Constants.OK);

        return retMap;
    }

}
