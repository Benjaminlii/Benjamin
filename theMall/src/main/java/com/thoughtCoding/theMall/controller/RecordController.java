package com.thoughtCoding.theMall.controller;

import com.thoughtCoding.theMall.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.7.30
 */
@Controller
public class RecordController {

    @Autowired
    public RecordService recordService;

    /**
     * 得到(某类型物品)某一年按月份统计的出货量
     */
    @RequestMapping(value = "/totalNumberByMonth")
    @ResponseBody
    public Map<String, Integer> totalNumberByMonth(HttpServletRequest request,
                                                   @RequestParam(value = "recordType", required = false) String recordType,
                                                   @RequestParam(value = "year", required = false) int year) {
        Map<String, Integer> map = recordService.queryCountByMonth(recordType, year);
        return map;
    }

    /**
     * 得到(某类型物品)某一月按日统计的出货量
     */
    @RequestMapping(value = "/totalNumberByDay")
    @ResponseBody
    public Map<String, Integer> totalNumberByDay(HttpServletRequest request,
                                                 @RequestParam(value = "recordType", required = false) String recordType,
                                                 @RequestParam(value = "year", required = false) int year,
                                                 @RequestParam(value = "month", required = false) int month) {
        Map<String, Integer> map = recordService.queryCountByDay(recordType, year, month);
        return map;
    }

    /**
     * 得到(某类型物品)按年龄划分的近一年出货量
     */
    @RequestMapping(value = "/totalNumberByAge")
    @ResponseBody
    public Map<String, Integer> totalNumberByAge(HttpServletRequest request,
                                                @RequestParam(value = "recordType", required = false) String recordType) {

        Map<String, Integer> map = recordService.queryCountByAge(recordType);
        return map;
    }
}
