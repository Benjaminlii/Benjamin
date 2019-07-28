package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.common.util.HttpClientUtils;
import com.Benjamin.p2p.config.Config;
import com.Benjamin.p2p.model.user.User;
import com.Benjamin.p2p.model.vo.ResultObject;
import com.Benjamin.p2p.service.user.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * author:Benjamin
 * date:2019.7.27
 */
//@Controller
@RestController//相当于@Controller,并且在勒种的每个方法上都使用了@ResponseBody注解
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Config config;

    /**
     * 检查手机号是否存在
     */
    @RequestMapping(value = "/loan/checkPhone")
//    @ResponseBody
    public Object checkPhone(HttpServletRequest request,
                             @RequestParam(value = "phone", required = true) String phone) {
        Map<String, String> retMap = new HashMap<>();

        //查询手机号是否重复
        User user = userService.queryUserByPhone(phone);

        //判断是否查询到user对象
        if (user != null) {
            retMap.put(Constants.ERROR_MESSAGE, "该手机号已经被注册,请更换号码");
            return retMap;
        }

        retMap.put(Constants.ERROR_MESSAGE, Constants.OK);

        return retMap;
    }

    /**
     * 验证图形验证码
     */
    @RequestMapping(value = "/loan/checkCaptcha", method = RequestMethod.POST)//等同于@PostMapping(....)
//    @ResponseBody
    public Map<String, Object> checkCaptcha(HttpServletRequest request,
                                            @RequestParam(value = "captcha", required = true) String captcha) {
        Map<String, Object> retMap = new HashMap<>();

        //获取session中的图形验证码
        String sessionCaptcha = (String) request.getSession().getAttribute(Constants.CAPTCHA);

        //验证是否一致,使用common-lang3工具类不区分大小写判断
        if (!StringUtils.equalsIgnoreCase(captcha, sessionCaptcha)) {
            retMap.put(Constants.ERROR_MESSAGE, "请输入正确的图形验证码");
            return retMap;
        }
        retMap.put(Constants.ERROR_MESSAGE, Constants.OK);

        return retMap;
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/loan/register")
    public Map<String, Object> register(HttpServletRequest request,
                                        @RequestParam(value = "phone", required = true) String phone,
                                        @RequestParam(value = "loginPassword", required = true) String loginPassword,
                                        @RequestParam(value = "replayLoginPassword", required = true) String replayLoginPassword) {
        Map<String, Object> retMap = new HashMap<>();

        //验证信息
        //手机号格式和重复
        if (!Pattern.matches("^1[1-9]\\d{9}$", phone) || userService.queryUserByPhone(phone) != null) {
            retMap.put(Constants.ERROR_MESSAGE, "请输入正确的手机号码");
            return retMap;
        }
        //密码一致
        if (!StringUtils.equals(loginPassword, replayLoginPassword)) {
            retMap.put(Constants.ERROR_MESSAGE, "两次密码输入不一致");
            return retMap;
        }

        //用户注册(1.新增用户 2.开立账户)
        ResultObject resultObject = userService.register(phone, loginPassword);

        //是否成功
        if (!StringUtils.equals(Constants.SUCCESS, resultObject.getErrorCode())) {
            //失败
            retMap.put(Constants.ERROR_MESSAGE, "注册失败.请稍后重试");
        }

        //注册成功,将用户信息放入session
        request.getSession().setAttribute(Constants.SESSION_USER, userService.queryUserByPhone(phone));

        retMap.put(Constants.ERROR_MESSAGE, Constants.OK);

        return retMap;
    }

    /**
     * 实名认证
     */
    @RequestMapping(value = "/loan/verifyRealName")
    public Map<String, Object> verifyRealName(HttpServletRequest request,
                                 @RequestParam(value = "realName", required = true) String realName,
                                 @RequestParam(value = "idCard", required = true) String idCard,
                                 @RequestParam(value = "replayIdCard", required = true) String replayIdCard) {

        Map<String, Object> retMap = new HashMap<>();

        //验证参数
        if (!Pattern.matches("[\\u4e00-\\u9fa5]+", realName)) {
            retMap.put(Constants.ERROR_MESSAGE, "真实姓名只支持中文");
            return retMap;
        }
        if (!Pattern.matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", idCard)) {
            retMap.put(Constants.ERROR_MESSAGE, "请输入正确的身份证号码");
            return retMap;

        }
        if (!StringUtils.equals(idCard, replayIdCard)) {
            retMap.put(Constants.ERROR_MESSAGE, "两次输入身份证号码不一致");
            return retMap;
        }

        //实名认证
        //构造参数表
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appkey", config.getRealNameAppKey());
        paramMap.put("cardNo", idCard);
        paramMap.put("realName", realName);

        //调用API,发送请求验证用户信息
        String responseString = HttpClientUtils.doPost(config.getRealNameUrl(), paramMap);

        //JSON格式字符串转化为JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(responseString);

        //获取反馈的数据
        String code = jsonObject.getString("code");
        if (StringUtils.equals(code, "10000")) {
            //通信成功,获取isok标识
            Boolean isok = jsonObject.getJSONObject("result").getJSONObject("result").getBoolean("isok");
            if (isok) {
                //实名认证成功,更新用户信息
                User updateUser = new User();
                //查询数据库中原有的user的数据,用于构造新的user数据
                User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
                updateUser.setId(user.getId());
                updateUser.setIdCard(idCard);
                updateUser.setName(realName);
                int modifyUserCount = userService.modifyUserByUid(updateUser);
                if(modifyUserCount > 0){
                    //更新session中的user信息
                    request.getSession().setAttribute(Constants.SESSION_USER, userService.queryUserByPhone(user.getPhone()));
                    retMap.put(Constants.ERROR_MESSAGE, Constants.OK);

                }else{
                    retMap.put(Constants.ERROR_MESSAGE, "系统繁忙,请稍后重试.");
                    return retMap;
                }
            } else {
                //认证失败
                retMap.put(Constants.ERROR_MESSAGE, "真实姓名与身份证号码不匹配.");
                return retMap;
            }
        } else {
            //通信失败
            retMap.put(Constants.ERROR_MESSAGE, "通信失败,请稍后重试.");
            return retMap;
        }

        return retMap;
    }
}
