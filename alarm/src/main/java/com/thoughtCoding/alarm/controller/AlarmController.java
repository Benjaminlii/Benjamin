package com.thoughtCoding.alarm.controller;

import com.thoughtCoding.alarm.model.Constant;
import com.thoughtCoding.alarm.model.User;
import com.thoughtCoding.alarm.service.ContactService;
import com.thoughtCoding.alarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:AlarmController
 * Package:com.thoughtCoding.alarm.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午4:59
 */
@Controller
public class AlarmController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    /**
     * 用户登录
     * @param phone 登录使用的手机号
     * @param password 登录密码
     * @return 校验成功返回true,否则返回false
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Object login(@RequestParam(value = "phone", required = true) String phone,
                        @RequestParam(value = "password", required = true) String password,
                        HttpServletRequest request){
        Map<String, String> rtn = new HashMap<>();

        User user;
        if(!((user = userService.queryUserByPhoneAndPassword(phone, password)) ==null)){
            // 如果登录成功,将登录对象添加到session
            request.getSession().setAttribute(Constant.USER, user);
            rtn.put("code", Constant.SUCCESS);
        }else{
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号或密码不正确");
        }

        return rtn;
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Object register(@RequestParam(value = "phone", required = true) String phone,
                           @RequestParam(value = "password", required = true) String password,
                           HttpServletRequest request){
        Map<String, String> rtn = new HashMap<>();
        if(phone.length() != 11){
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号格式不正确");
            return rtn;
        }
        if(password.length() < 6 || password.length() > 20){
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "密码格式不正确");
            return rtn;
        }

        if(userService.addUser(phone, password)){
            rtn.put("code", Constant.SUCCESS);
        }else{
            rtn.put("code", "注册失败");
        }

        return rtn;
    }
}
