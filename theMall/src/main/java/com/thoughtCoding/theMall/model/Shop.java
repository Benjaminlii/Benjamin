package com.thoughtCoding.theMall.model;

/**
 * author:Benjamin
 * date:2019.7.19
 */
public class Shop {
    /**
     * 店铺id
     */
    private Byte shopId;

    /**
     * 店铺姓名
     */
    private String shopName;

    public Byte getShopId() {
        return shopId;
    }

    public void setShopId(Byte shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}