package com.thoughtCoding.theMall.controller;

import com.thoughtCoding.theMall.model.BlackRecord;
import com.thoughtCoding.theMall.service.BlackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:BlackRecordController
 * Package:com.thoughtCoding.theMall.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-9-17 下午3:59
 */

@Controller
public class BlackRecordController {
    @Autowired
    private BlackRecordService blackRecordService;

    @RequestMapping(value = "/queryBlack")
    @ResponseBody
    public List<BlackRecord> queryBlack(HttpServletRequest request,
                                        @RequestParam(value = "year", required = true) String year,
                                        @RequestParam(value = "month", required = true) String month,
                                        @RequestParam(value = "day", required = true) String day) {
        Date timeStamp = new Date(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));

        List<BlackRecord> list = blackRecordService.queryBlackRecordByTimestamp(timeStamp);

        return list;
    }

}
