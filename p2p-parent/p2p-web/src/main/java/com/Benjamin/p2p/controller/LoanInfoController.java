package com.Benjamin.p2p.controller;

import com.Benjamin.p2p.model.loan.BidInfo;
import com.Benjamin.p2p.model.loan.LoanInfo;
import com.Benjamin.p2p.model.vo.PaginatinoVo;
import com.Benjamin.p2p.service.loan.BidInfoService;
import com.Benjamin.p2p.service.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.7.26
 */
@Controller
public class LoanInfoController {

    @Autowired
    private LoanInfoService loanInfoService;

    @Autowired
    private BidInfoService bidInfoService;

    /**
     * 初始化loan页面
     */
    @RequestMapping(value = "/loan/loan")
    public String loan(HttpServletRequest request, Model model,
                       @RequestParam(value = "ptype", required = false) Integer ptype,
                       @RequestParam(value = "currentPage", required = false) Integer currentPage) {

        //判断当前页码是否为空,为空,默认第一页
        if (currentPage == null) {
            currentPage = 1;
        }

        //分页查询参数
        Map<String, Object> paramMap = new HashMap<>();
        if (ptype != null) {
            paramMap.put("productType", ptype);
        }
        int pageSize = 9;

        //起始下标
        paramMap.put("currentPage", (currentPage - 1) * pageSize);

        //截取的条数
        paramMap.put("pageSize", pageSize);

        //分页查询产品信息列表(产品类型,页码,每页显示几条),返回分页模型对象(总记录条数,当前页要显示的数据)
        PaginatinoVo<LoanInfo> paginatinoVo = loanInfoService.queryLoanInfoByPage(paramMap);

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
        model.addAttribute("loanInfoList", paginatinoVo.getDataList());
        //当前页码
        model.addAttribute("currentPage", currentPage);
        //产品类型
        if (ptype != null)
            model.addAttribute("ptype", ptype);

        //TODO
        //用户投资排行榜

        return "loan";
    }

    @RequestMapping(value = "/loan/loanInfo")
    public String loanInfo(HttpServletRequest request, Model model,
                           @RequestParam(value = "id", required = true) Integer id){
        //根据产品标识获取产品的详情
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(id);


        //根据产品标识获取该产品的所有投资记录
        List<BidInfo> bidInfoList = bidInfoService.queryBidListByLoanId(id);


        model.addAttribute("loanInfo", loanInfo);
        model.addAttribute("bidInfoList", bidInfoList);

        //TODO
        //获取当前用户的账户可用余额

        return "loanInfo";
    }
}
