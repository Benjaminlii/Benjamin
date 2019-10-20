package com.thoughtCoding.alarm.model;

/**
 * ClassName:Constant
 * Package:com.thoughtCoding.alarm.model
 * <p>
 * Description:
 * alarm项目中用到的一些常量
 *
 * @author: Benjamin
 * @date: 19-10-17 下午5:02
 */
public class Constant {
    /**
     * 请求响应失败,返回{"code", Constant.SUCCESS}
     */
    public static final String ERROR = "error";

    /**
     * 请求响应成功,返回{"code", Constant.SUCCESS}
     */
    public static final String SUCCESS = "success";

    /**
     * 当前登录用户
     */
    public static final String USER = "user";

    /**
     * 短信调用接口url
     */
    public static final String SMS_URL = "https://service-r6vq2kpa-1259648947.gz.apigw.tencentcs.com/release/send/code";

    /**
     * 添加联系人验证码短信的模板
     */
    public static final String RESIGER_VERCODE_SSM = "【TC科技小组】alarm-您的验证码是%s。请不要把验证码泄露给别人。如非本人操作，请忽略此短信。";

    /**
     * 添加联系人验证码短信的模板
     */
    public static final String ADD_CONTACT_VERCODE_SSM = "【TC科技小组】alarm-您的验证码是%s，手机号码为%s的账号申请你成为紧急联系人。";

    /**
     * 报警短信的模板
     */
    public static final String ALARM_SSM = "【TC科技小组】alarm-手机号码为%s的用户目前不安全,位置:%s,请尝试联系或报警。";

    /**
     * 全局保存当前用户调用接口发送的添加联系人短信验证码
     */
    public static final String UID_VERCODE_CALL_PHONE = "uid_verCode_call_phone";

    /**
     * 全局保存当前用户调用接口发送的注册短信验证码
     */
    public static final String PHONE_VERCODE = "phone_verCode";

    /**
     * 向安卓端看对信息的数据字段
     */
    public static final String DATA = "data";
}
