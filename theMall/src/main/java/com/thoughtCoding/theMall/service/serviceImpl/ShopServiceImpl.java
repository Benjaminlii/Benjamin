package com.thoughtCoding.theMall.service.serviceImpl;

import com.thoughtCoding.theMall.mapper.ShopMapper;
import com.thoughtCoding.theMall.model.Shop;
import com.thoughtCoding.theMall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:Benjamin
 * date:2019.7.30
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Shop queryShopByShopName(String shopName) {
        return shopMapper.selectShopByShopName(shopName);
    }

    @Override
    public List<Shop> queryAllShops() {
        return shopMapper.selectAllShops();
    }
}
