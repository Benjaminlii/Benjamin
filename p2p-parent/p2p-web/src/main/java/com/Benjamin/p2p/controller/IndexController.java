package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.model.loan.BidInfo;
import com.Benjamin.p2p.model.loan.IncomeRecord;
import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.model.loan.RechargeRecord;
import com.Benjamin.p2p.model.user.User;
import com.Benjamin.p2p.model.vo.PaginatinoVo;
import com.Benjamin.p2p.service.loan.BidInfoService;
import com.Benjamin.p2p.service.loan.IncomeRecordService;
import com.Benjamin.p2p.service.loan.LoanInfoService;
import com.Benjamin.p2p.service.loan.RechargeRecordService;
import com.Benjamin.p2p.service.user.UserService;
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

    @Autowired
    private RechargeRecordService rechargeRecordService;

    @Autowired
    private IncomeRecordService incomeRecordService;

    /**
     * 首页信息初始化查询
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {

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
        List<LoanInfo> xLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);

        //获取优选产品:产品类型1,显示第1页,每页显示4个
        paramMap.put("productType", Constants.PRODUCT_TYPE_U);
        paramMap.put("pageSize", 4);
        List<LoanInfo> uLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);

        //获取散标产品:产品类型2,显示第1页,每页显示8个
        paramMap.put("productType", Constants.PRODUCT_TYPE_S);
        paramMap.put("pageSize", 8);
        List<LoanInfo> sLoanInfoList = loanInfoService.queryLoanInfoByProductType(paramMap);

        model.addAttribute("xLoanInfoList", xLoanInfoList);
        model.addAttribute("uLoanInfoList", uLoanInfoList);
        model.addAttribute("sLoanInfoList", sLoanInfoList);

        return "index";
    }

    @RequestMapping(value = "/loan/loadStat")
    @ResponseBody
    public Map<String, Object> loadStat(HttpServletRequest request) {
        Map<String, Object> retMap = new HashMap<>();

        //获取历史平均年化收益率
        double historyAverageRate = loanInfoService.queryHistoryAverageRate();
        retMap.put(Constants.HISTORY_AVERAGE_RATE, historyAverageRate);

        //获取平台注册总人数
        Long allUserCount = userService.queryAllUserCount();
        retMap.put(Constants.ALL_USER_COUNT, allUserCount);

        //获取平台积累投资金额
        Double allBidMoney = bidInfoService.queryAllBidMoney();
        retMap.put(Constants.ALL_BID_MONEY, allBidMoney);

        return retMap;

    }

    @RequestMapping(value = "loan/myInvest")
    private String myInvest(HttpServletRequest request, Model model,
                            @RequestParam(value = "currentPage", required = false) Integer currentPage) {
        //判断当前页码是否为空,为空,默认第一页
        if (currentPage == null) {
            currentPage = 1;
        }

        //分页查询参数
        Map<String, Object> paramMap = new HashMap<>();
        int pageSize = 9;

        //起始下标
        paramMap.put("currentPage", (currentPage - 1) * pageSize);

        //截取的条数
        paramMap.put("pageSize", pageSize);

        //用户信息
        User sessionUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        paramMap.put("userId", sessionUser.getId());

        //分页查询产品信息列表(产品类型,页码,每页显示几条),返回分页模型对象(总记录条数,当前页要显示的数据)
        PaginatinoVo<BidInfo> paginatinoVo = bidInfoService.queryBidInfoByPage(paramMap);

        //计算总页数
        int totalPage = paginatinoVo.getTotal().intValue() / pageSize;
        //求余
        int mod = paginatinoVo.getTotal().intValue() % pageSize;
        if (mod > 0) {
            totalPage++;
        }

        //总记录数
        model.addAttribute("totalRows", paginatinoVo.getTotal());
        //总页数
        model.addAttribute("totalPage", totalPage);
        //每页显示数据
        model.addAttribute("bidInfoList", paginatinoVo.getDataList());
        //当前页码
        model.addAttribute("currentPage", currentPage);

        return "myInvest";
    }

    @RequestMapping(value = "/loan/myRecharge")
    public String myRecharge(HttpServletRequest request, Model model,
                             @RequestParam(value = "currentPage", required = false) Integer currentPage) {
        //判断当前页码是否为空,为空,默认第一页
        if (currentPage == null) {
            currentPage = 1;
        }

        //分页查询参数
        Map<String, Object> paramMap = new HashMap<>();
        int pageSize = 9;

        //起始下标
        paramMap.put("currentPage", (currentPage - 1) * pageSize);

        //截取的条数
        paramMap.put("pageSize", pageSize);

        //用户信息
        User sessionUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        paramMap.put("userId", sessionUser.getId());

        //分页查询产品信息列表(产品类型,页码,每页显示几条),返回分页模型对象(总记录条数,当前页要显示的数据)
        PaginatinoVo<RechargeRecord> paginatinoVo = rechargeRecordService.queryRechargeByPage(paramMap);


        //计算总页数
        int totalPage = paginatinoVo.getTotal().intValue() / pageSize;
        //求余
        int mod = paginatinoVo.getTotal().intValue() % pageSize;
        if (mod > 0) {
            totalPage++;
        }

        //总记录数
        model.addAttribute("totalRows", paginatinoVo.getTotal());
        //总页数
        model.addAttribute("totalPage", totalPage);
        //每页显示数据
        model.addAttribute("rechargeRecordList", paginatinoVo.getDataList());
        //当前页码
        model.addAttribute("currentPage", currentPage);

        return "myRecharge";
    }

    @RequestMapping(value = "/loan/myIncome")
    public String myIncome(HttpServletRequest request, Model model,
                           @RequestParam(value = "currentPage", required = false) Integer currentPage) {
        //判断当前页码是否为空,为空,默认第一页
        if (currentPage == null) {
            currentPage = 1;
        }

        //分页查询参数
        Map<String, Object> paramMap = new HashMap<>();
        int pageSize = 9;

        //起始下标
        paramMap.put("currentPage", (currentPage - 1) * pageSize);

        //截取的条数
        paramMap.put("pageSize", pageSize);

        //用户信息
        User sessionUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        paramMap.put("userId", sessionUser.getId());

        //分页查询产品信息列表(产品类型,页码,每页显示几条),返回分页模型对象(总记录条数,当前页要显示的数据)
        PaginatinoVo<IncomeRecord> paginatinoVo = incomeRecordService.queryIncomeByPage(paramMap);

        //计算总页数
        int totalPage = paginatinoVo.getTotal().intValue() / pageSize;
        //求余
        int mod = paginatinoVo.getTotal().intValue() % pageSize;
        if (mod > 0) {
            totalPage++;
        }

        //总记录数
        model.addAttribute("totalRows", paginatinoVo.getTotal());
        //总页数
        model.addAttribute("totalPage", totalPage);
        //每页显示数据
        model.addAttribute("incomeRecordList", paginatinoVo.getDataList());
        //当前页码
        model.addAttribute("currentPage", currentPage);

        return "myIncome";
    }

    @RequestMapping(value = "/loan/myAccount")
    public String myAccount(HttpServletRequest request, Model model) {


        return "myAccount";
    }
}
