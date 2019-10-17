package com.thoughtCoding.alarm.controller;

import com.thoughtCoding.alarm.model.Constant;
import com.thoughtCoding.alarm.model.User;
import com.thoughtCoding.alarm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/addContact")
    @ResponseBody
    public Object addContact(@RequestParam(value = "uid", required = false) Short uid,
                             @RequestParam(value = "callPhone", required = true) String callPhone,
                             HttpServletRequest request) {
        Map<String, String> rtn = new HashMap<>();

        // 如果没有传入uid参数(postman测试),从session中获取uid
        if (uid == null) {
            uid = ((User) request.getSession().getAttribute(Constant.USER)).getUserId();
        }
        if (callPhone.length() != 11) {
            rtn.put("code", Constant.ERROR);
            rtn.put("errorMessage", "手机号格式不正确!");
            return rtn;
        }
        // TODO

    }
}
