package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.common.util.DateUtils;
import com.Benjamin.p2p.common.util.HttpClientUtils;
import com.Benjamin.p2p.model.loan.RechargeRecord;
import com.Benjamin.p2p.model.user.User;
import com.Benjamin.p2p.service.loan.OnlyNumberService;
import com.Benjamin.p2p.service.loan.RechargeRecordService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.8.4
 */
@Controller
public class RechargeRecordController {

    @Autowired
    private RechargeRecordService rechargeRecordService;

    @Autowired
    private OnlyNumberService onlyNumberService;

    @RequestMapping(value = "/loan/toAlipayRecharge")
    public String toAlipayRecharge(HttpServletRequest request, Model model,
                                   @RequestParam(value = "rechargeMoney", required = true) Double rechargeMoney) {
        //从session中获取用户信息
        User sessionUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        String rechargeNo = DateUtils.getTimeStamp() + onlyNumberService.getOnlyNumber();

        //生成全局唯一订单号--->时间戳+redis的自加数
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setRechargeMoney(rechargeMoney);
        rechargeRecord.setRechargeTime(new Date());
        rechargeRecord.setRechargeNo(rechargeNo);
        rechargeRecord.setRechargeDesc("支付宝充值");
        rechargeRecord.setRechargeStatus(String.valueOf(0));
        rechargeRecord.setUid(sessionUser.getId());

        Integer addCount = rechargeRecordService.addRechargeRecord(rechargeRecord);

        //生成充值记录(状态为充值中)(uid,充值金额,当前时间,充值订单号,充值状态,充值描述)
        if (addCount > 0) {
            //向pay工程的支付方法传递参数
            model.addAttribute("p2p_alipay_pay_url", "http://localhost:8082/pay/api/alipay");
            model.addAttribute("out_trade_no", rechargeNo);
            model.addAttribute("total_amount", rechargeMoney);
            model.addAttribute("subject", "支付宝充值");
        } else {
            model.addAttribute("trade_msg", "系统繁忙,清朝后再试.");
            return "toRechargeBack";
        }

        return "toAlipay";
    }

    @RequestMapping(value = "/loan/alipayBack")
    public String alipayBack(HttpServletRequest request, Model model,
                             @RequestParam(value = "out_trade_no", required = true) String out_trade_no,
                             @RequestParam(value = "signVerified", required = true) String signVerified,
                             @RequestParam(value = "total_amount", required = true) String total_amount
    ) {

        System.out.println("---------------p2p-web---alipayBack---------------");

        //判断签名是否成功
        if (StringUtils.equals(Constants.SUCCESS, signVerified)) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("out_trade_no", out_trade_no);
            //成功调用pay工程订单查询接口查看订单的状态,处理相应的业务逻辑
            String result = HttpClientUtils.doPost("http://localhost:8082/pay/api/alipayQuery", paramMap);

            //使用fastJson解析json字符串
            JSONObject jsonObject = JSONObject.parseObject(result);

            //得到响应判定的结果
            JSONObject tradeQueryResponse = jsonObject.getJSONObject("alipay_trade_query_response");
            //得到通信标识
            String code = tradeQueryResponse.getString("code");

            if(StringUtils.equals("10000", code)){
                //通信成功
                String tradeStatus = tradeQueryResponse.getString("trade_status");

                /*
                * 交易状态
                * WAIT_BUYER_PAY 交易创建,等待买家付款
                * TRADE_CLOSED 交易超市关闭,或支付完成后全额退款
                * TRADE_SUCCESS 交易支付成功
                * TRADE_FINISHED 交易结束不可退款
                * */
                if(StringUtils.equals(tradeStatus, "TRADE_CLOSED")){
                    //更新充值记录为2,标识充值失败
                    RechargeRecord updateRechargeRecord = new RechargeRecord();
                    updateRechargeRecord.setRechargeNo(out_trade_no);
                    updateRechargeRecord.setRechargeStatus(String.valueOf(2));
                    Integer modifyCount = rechargeRecordService.modifyRechargeRecordByRechargeNo(updateRechargeRecord);

                    if(modifyCount <= 0){
                        model.addAttribute("trade_msg", "通信失败请稍后再试");
                        return "toRechargeBack";
                    }
                }
                if(StringUtils.equals(tradeStatus, "TRADE_SUCCESS")){
                    User sessionUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);
                    //给用户充值[更新账户可用余额,更新充值记录状态为1]
                    paramMap.put("userId", sessionUser.getId());
                    paramMap.put("rechargeNo", out_trade_no);
                    paramMap.put("rechargeMoney", total_amount);
                    Integer rechargeCount = rechargeRecordService.recharge(paramMap);

                    if(rechargeCount <= 0){
                        model.addAttribute("trade_msg", "充值人数过多,请稍后再试.");
                        return "toRechargeBack";
                    }

                }
            }else{
                //通信失败
                model.addAttribute("trade_msg", "通信失败请稍后再试");
                return "toRechargeBack";
            }

        } else {
            //失败
            return "toRechargeBack";

        }

        return "redirect:/loan/myCenter";
    }

    @RequestMapping(value = "/loan/toWxpayRecharge")
    public String toWxpayRecharge(HttpServletRequest request, Model model,
                                  @RequestParam(value = "rechargeMoney", required = true) Double rechargeMoney) {


        return "";
    }


}
