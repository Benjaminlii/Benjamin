package com.thoughtCoding.alarm.controller;

import com.alibaba.fastjson.JSONObject;
import com.thoughtCoding.alarm.model.Constant;
import com.thoughtCoding.alarm.model.User;
import com.thoughtCoding.alarm.service.ContactService;
import com.thoughtCoding.alarm.service.UserService;
import com.thoughtCoding.alarm.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private SMSUtil smsUtil;

    /**
     * 用户登录
     *
     * @param phone    登录使用的手机号
     * @param password 登录密码
     * @return 校验成功返回true, 否则返回false
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Object login(@RequestParam(value = "phone", required = true) String phone,
                        @RequestParam(value = "password", required = true) String password,
                        HttpServletRequest request) {
        System.out.println("sessionId:" + request.getSession().getId());

        Map<String, String> rtn = new HashMap<>();

        User user;
        if (!((user = userService.queryUserByPhoneAndPassword(phone, password)) == null)) {
            // 如果登录成功,将登录对象添加到session
            request.getSession().setAttribute(Constant.USER, user);
            rtn.put("code", Constant.SUCCESS);
        } else {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号或密码不正确");
        }

        return rtn;
    }

    @ResponseBody
    @RequestMapping(value = "/sendRegisterSMS")
    public Object sendRegisterSMS(@RequestParam(value = "phone", required = true) String phone,
                                  HttpServletRequest request) {
        Map<String, String> rtn = new HashMap<>();
        String httpRtn = null;

        // 手机号正确,也取到了uid
        // 发送内容
        Map<String, Object> paraMap = new HashMap<>();
        String verCode = new Random(new Date().getTime()).nextInt(100000) + "";
        request.getSession().setAttribute(Constant.PHONE_VERCODE, phone + "-" + verCode);

        paraMap.put("content", String.format(Constant.RESIGER_VERCODE_SSM, verCode));
        paraMap.put("mobile", phone);
        try {
            httpRtn = smsUtil.sms(Constant.SMS_URL, paraMap);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        JSONObject rtnJson = JSONObject.parseObject(httpRtn);

        if (!"1".equals(rtnJson.getString("status"))) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "短信接口调用出错");
            return rtn;
        }
        rtn.put("code", Constant.SUCCESS);

        return rtn;
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Object register(@RequestParam(value = "phone", required = true) String phone,
                           @RequestParam(value = "password", required = true) String password,
                           @RequestParam(value = "verCode", required = true) String verCode,
                           HttpServletRequest request) {
        Map<String, String> rtn = new HashMap<>();
        if (phone.length() != 11) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号格式不正确");
            return rtn;
        }
        if (password.length() < 6 || password.length() > 20) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "密码格式不正确");
            return rtn;
        }
        String pv = (String) request.getSession().getAttribute(Constant.PHONE_VERCODE);
        if (!pv.equals(phone + "-" + verCode)) {
            System.out.println();
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "验证码不正确");
            return rtn;
        }

        if (!userService.addUser(phone, password)) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号重复");
            return rtn;
        }

        rtn.put("code", Constant.SUCCESS);

        return rtn;
    }
}
