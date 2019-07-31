package com.thoughtCoding.theMall.model;

public class User {
    /**
     * 用户id
     */
    private Short userId;

    /**
     * 用户真实姓名
     */
    private String userRealName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 所属店铺id
     */
    private Byte shopId;

    /**
     * 用户类型
     * 0为店长
     * 1为店员
     */
    private Byte userType;

    /**
     * 所在店铺
     */
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getShopId() {
        return shopId;
    }

    public void setShopId(Byte shopId) {
        this.shopId = shopId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }
}