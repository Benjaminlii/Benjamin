package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.service.loan.BidInfoService;
import com.Benjamin.p2p.service.loan.LoanInfoService;
import com.Benjamin.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：Benjamin
 * date：2019.7.25
 */
@Controller
public class IndexController {

    @Autowired
    private LoanInfoService loanInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private BidInfoService bidInfoService;

    /**
     * 首页信息初始化查询
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model){

        //获取历史平均年化收益率
        double historyAverageRate = loanInfoService.queryHistoryAverageRate();
        model.addAttribute(Constants.HISTORY_AVERAGE_RATE, historyAverageRate);

        //获取平台注册总人数
        Long allUserCount = userService.queryAllUserCount();
        model.addAttribute(Constants.ALL_USER_COUNT, allUserCount);

        //获取平台积累投资金额
        Double allBidMoney = bidInfoService.queryAllBidMoney();
        model.addAttribute(Constants.ALL_BID_MONEY, allBidMoney);

        //以下查询看成是一个分页,实际功能是根据产品类型查询产品信息的前几个
        //loanInfoService.queryLoanInfoByProductType(产品类型,页码,每页显示条数)
        //参数使用map传入
        Map<String, Object> paramMap = new HashMap<>();

        //都是显示第1页,数据库中是0
        paramMap.put("currentPage", 0);

        //获取新手宝产品:产品类型0,显示第1页,每页显示1个
        paramMap.put("productType", Constants.PRODUCT_TYPE_X);
        paramMap.put("pageSize", 1);
        List<LoanInfo>xLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);

        //获取优选产品:产品类型1,显示第1页,每页显示4个
        paramMap.put("productType", Constants.PRODUCT_TYPE_U);
        paramMap.put("pageSize", 4);
        List<LoanInfo>uLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);

        //获取散标产品:产品类型2,显示第1页,每页显示8个
        paramMap.put("productType", Constants.PRODUCT_TYPE_S);
        paramMap.put("pageSize", 8);
        List<LoanInfo>sLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);

        model.addAttribute("xLoanInfoList", xLoanInfoList);
        model.addAttribute("uLoanInfoList", uLoanInfoList);
        model.addAttribute("sLoanInfoList", sLoanInfoList);

        return "index";
    }
}
