package com.thoughtCoding.alarm.controller;

import com.alibaba.fastjson.JSONObject;
import com.thoughtCoding.alarm.model.Constant;
import com.thoughtCoding.alarm.model.Contact;
import com.thoughtCoding.alarm.model.User;
import com.thoughtCoding.alarm.service.ContactService;
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
import java.util.*;

/**
 * ClassName:ContactController
 * Package:com.thoughtCoding.alarm.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-17 下午8:10
 */
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SMSUtil smsUtil;

    @RequestMapping("/sendVerMessage")
    @ResponseBody
    public Object sendVerMessage(@RequestParam(value = "uid", required = false) Short uid,
                                 @RequestParam(value = "callPhone", required = true) String callPhone,
                                 HttpServletRequest request) {
        Map<String, String> rtn = new HashMap<>();
        String httpRtn = null;

        // 如果没有传入uid参数(postman测试),从session中获取uid
        if (uid == null) {
            uid = ((User) request.getSession().getAttribute(Constant.USER)).getUserId();
        }
        if (uid == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "当前无登录状态!");
            return rtn;
        }
        if (callPhone.length() != 11) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号格式不正确!");
            return rtn;
        }

        // 手机号正确,也取到了uid
        // 发送内容
        Map<String, Object> paraMap = new HashMap<>();
        String verCode = new Random(new Date().getTime()).nextInt(100000) + "";
        request.getSession().setAttribute(Constant.UID_VERCODE_CALL_PHONE, uid + "-" + verCode + "-" + callPhone);

        // 当前用户的手机号,测试使用默认手机号
        User user = (User) request.getSession().getAttribute(Constant.USER);
        String phone = user!=null?user.getPhone():"15991075603";
        if (phone == null) phone = "15991075603";

        paraMap.put("content", String.format(Constant.ADD_CONTACT_VERCODE_SSM, verCode, phone));
        paraMap.put("mobile", callPhone);
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

    @RequestMapping("/addContact")
    @ResponseBody
    public Object addContact(@RequestParam(value = "uid", required = false) Short uid,
                             @RequestParam(value = "callPhone", required = true) String callPhone,
                             @RequestParam(value = "verCode", required = true) String verCode,
                             HttpServletRequest request) {
        Map<String, String> rtn = new HashMap<>();

        // 如果没有传入uid参数(postman测试),从session中获取uid
        if (uid == null) {
            uid = ((User) request.getSession().getAttribute(Constant.USER)).getUserId();
        }
        if (uid == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "当前无登录状态!");
            return rtn;
        }
        String realVerCode = (String) request.getSession().getAttribute(Constant.UID_VERCODE_CALL_PHONE);
        if (realVerCode == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "该用户尚未获取验证码");
            return rtn;
        } else if (!(uid + "-" + verCode + "-" + callPhone).equals(realVerCode)) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "验证码不正确");
            return rtn;
        }

        // 验证通过,进行业务逻辑部分
        if (!contactService.addContact(uid, callPhone)) {
            //添加失败
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "服务器繁忙");
            return rtn;
        }

        rtn.put("code", Constant.SUCCESS);

        return rtn;
    }

    @RequestMapping("/getContacts")
    @ResponseBody
    public Object getContacts(@RequestParam(value = "uid", required = false) Short uid,
                              HttpServletRequest request) {
        Map<String, Object> rtn = new HashMap<>();
        // 如果没有传入uid参数(postman测试),从session中获取uid
        if (uid == null) {
            uid = ((User) request.getSession().getAttribute(Constant.USER)).getUserId();
        }
        if (uid == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "当前无登录状态!");
            return rtn;
        }

        List<Contact> contactList = contactService.findAllContactsByUid(uid);

        if (contactList == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "服务器繁忙");
            return rtn;
        }

        rtn.put("code", Constant.SUCCESS);
        rtn.put(Constant.DATA, contactList);

        return rtn;
    }

    @RequestMapping("/deleteContact")
    @ResponseBody
    public Object deleteContact(@RequestParam(value = "uid", required = false) Short uid,
                                @RequestParam(value = "callPhone", required = true) String callPhone,
                                HttpServletRequest request) {
        Map<String, Object> rtn = new HashMap<>();
        // 如果没有传入uid参数(postman测试),从session中获取uid
        if (uid == null) {
            uid = ((User) request.getSession().getAttribute(Constant.USER)).getUserId();
        }
        if (uid == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "当前无登录状态!");
            return rtn;
        }

        if (!contactService.deleteContact(uid, callPhone)) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "服务器繁忙");
            return rtn;
        }
        rtn.put("code", Constant.SUCCESS);

        return rtn;
    }

    @RequestMapping("/alarm")
    @ResponseBody
    public Object alarm(@RequestParam(value = "phone", required = false) String phone,
                        @RequestParam(value = "uid", required = false) Short uid,
                        @RequestParam(value = "address", required = true) String address,
                        HttpServletRequest request) {
        Map<String, Object> rtn = new HashMap<>();
        // 如果没有传入uid参数(postman测试),从session中获取uid
        if (phone == null) {
            phone = ((User) request.getSession().getAttribute(Constant.USER)).getPhone();
        }
        if (uid == null) {
            uid = ((User) request.getSession().getAttribute(Constant.USER)).getUserId();
        }

        if (phone == null || uid == null) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "当前无登录状态!");
            return rtn;
        }

        List<Contact> contactList = contactService.findAllContactsByUid(uid);
        int numOfSMS = contactService.alarmAllCallPhone(phone, address, contactList);
        if (numOfSMS != contactList.size()) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "部分发送信息");
            rtn.put("numOfSMS", numOfSMS);
            rtn.put("numOfCallPhone", contactList.size());
            return rtn;
        }
        rtn.put("code", Constant.SUCCESS);

        return rtn;
    }

}
