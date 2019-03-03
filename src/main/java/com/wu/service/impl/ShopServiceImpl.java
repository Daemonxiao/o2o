package com.wu.service.impl;

import com.wu.dao.ShopDao;
import com.wu.dto.ShopExecution;
import com.wu.entity.Shop;
import com.wu.enums.ShopStateEnum;
import com.wu.service.ShopService;
import com.wu.util.ImageUtil;
import com.wu.util.PageCalculator;
import com.wu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败");
            } else {
                if (shopImg != null) {
                    //存储图片
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new RuntimeException("addShopImg error:" + e.getMessage());
                    }
                    //更新图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum <= 0){
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) {
        try {
            //判断是否需要处理图片
            if (shop == null || shop.getShopId() == null) {
                return new ShopExecution(ShopStateEnum.NULL_SHOP);
            } else {
                if (shopImg != null) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());

                }
                addShopImg(shop, shopImg);
            }
            //更新图片信息
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if (effectedNum <= 0) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            } else {
                shop = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            }
        } catch (Exception e) {
            throw new RuntimeException("修改商店信息异常" + e.getMessage());
        }
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        //1获取shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
