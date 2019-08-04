package com.Benjamin.p2p.service.loan;

import com.Benjamin.p2p.mapper.loan.RechargeRecordMapper;
import com.Benjamin.p2p.model.loan.RechargeRecord;
import com.Benjamin.p2p.model.vo.PaginatinoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Benjamin
 * date:2019.8.4
 */
@Service
public class RechargeRecordServiceImpl implements RechargeRecordService {

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Override
    public List<RechargeRecord> queryRechargeByUidOrderByTime(Integer userId) {
        //封装参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        //分页查询每页3条数据,查询第一页
        paramMap.put("currentPage", 1);
        paramMap.put("pageSize", 3);

        return rechargeRecordMapper.selectRechargeByUidOrderByTime(paramMap);
    }

    @Override
    public PaginatinoVo<RechargeRecord> queryRechargeByPage(Map<String, Object> paramMap) {
        PaginatinoVo<RechargeRecord> paginatinoVo = new PaginatinoVo<>();
        Integer userId = (Integer) paramMap.get("userId");

        //先查询总记录数
        Long total = rechargeRecordMapper.selectTotalByUid(userId);
        paginatinoVo.setTotal(total);

        //查询数据列表
        List<RechargeRecord> rechargeRecordList = rechargeRecordMapper.selectRechargeByPage(paramMap);
        paginatinoVo.setDataList(rechargeRecordList);

        return paginatinoVo;
    }
}
