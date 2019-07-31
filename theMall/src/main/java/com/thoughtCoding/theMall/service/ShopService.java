package com.thoughtCoding.theMall.service;

import com.thoughtCoding.theMall.model.Shop;

import java.util.List;

/**
 * author:Benjamin
 * date:2019.7.30
 */
public interface ShopService {

    /**
     * 根据店铺名查询店铺
     */
    Shop queryShopByShopName(String shopName);

    /**
     * 获取全部的店铺信息列表
     */
    List<Shop> queryAllShops();

}
