package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.model.loan.BidInfo;
import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.model.user.User;
import com.Benjamin.p2p.model.vo.BidUserTop;
import com.Benjamin.p2p.model.vo.PaginatinoVo;
import com.Benjamin.p2p.model.vo.ResultObject;
import com.Benjamin.p2p.service.loan.BidInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BidInfoController {

    @Autowired
    private BidInfoService bidInfoService;

    @RequestMapping(value = "loan/invest")
    @ResponseBody
    public Map<String, Object> invest(HttpServletRequest request,
                      @RequestParam(value = "loanId", required = true) Integer loanId,
                      @RequestParam(value = "bidMoney", required = true) Double bidMoney){

        Map<String, Object> retMap = new HashMap<>();

        //从session中获取user信息
        User sessionUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);

        //封装参数
        Map<String , Object> paraMap = new HashMap<>();
        paraMap.put("userId", sessionUser.getId());
        paraMap.put("loanId", loanId);
        paraMap.put("bidMoney", bidMoney);
        paraMap.put("phone", sessionUser.getPhone());

        //用户投资(用户id, 产品id, 投资金额)--->ResultObject
        ResultObject resultObject = bidInfoService.invest(paraMap);

        //判断是否成功
        if(!StringUtils.equals(resultObject.getErrorCode(), Constants.SUCCESS)){
            //投资失败
            retMap.put(Constants.ERROR_MESSAGE, "服务器繁忙,请稍后再试.");
        }else{
            retMap.put(Constants.ERROR_MESSAGE, Constants.OK);
        }

        return retMap;
    }


}
