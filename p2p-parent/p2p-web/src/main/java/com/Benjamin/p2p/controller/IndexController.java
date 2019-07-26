package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.common.constant.Constans;
import com.Benjamin.p2p.service.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * author：Benjamin
 * date：2019.7.25
 */
@Controller
public class IndexController {

    @Autowired
    private LoanInfoService loanInfoService;

    /**
     * 首页信息初始化查询
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model){

        //获取历史平均年化收益率
        double historyAverageRate = loanInfoService.queryHistoryAverageRate();
        model.addAttribute(Constans.HISTORY_AVERAGE_RATE, historyAverageRate);

        //获取平台注册总人数

        //获取平台积累投资金额

        //获取新手宝产品

        //获取优选产品

        //获取散标产品

        return "test";
    }
}
