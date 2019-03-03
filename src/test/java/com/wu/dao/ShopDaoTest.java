package com.wu.dao;

import com.wu.BastTest;
import com.wu.entity.Area;
import com.wu.entity.PersonInfo;
import com.wu.entity.Shop;
import com.wu.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BastTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    @Ignore
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testUpdatetShop(){
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testQueryByShopId(){
        Long shopId = 2L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaName());
        System.out.println("shopcategoryname:" + shop.getShopCategory().getShopCategoryName());
    }

    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();
        PersonInfo owener = new PersonInfo();
        owener.setUserId(1L);
        shopCondition.setOwner(owener);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
        System.out.println("area:"+shopList.get(0).getArea().getAreaName());
        System.out.println("desc" + shopList.get(0).getShopDesc());
        System.out.println(shopList.size());
        System.out.println(shopList);
    }

}
