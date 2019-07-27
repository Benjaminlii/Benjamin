package com.Benjamin.p2p.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 *
 * author:Benjamin
 * date:2019.7.26
 */
public class PaginatinoVo<T> implements Serializable {

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 显示数据
     */
    private List<T> dataList;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}