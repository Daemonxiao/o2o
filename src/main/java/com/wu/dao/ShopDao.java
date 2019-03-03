package com.wu.dao;

import com.wu.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    //新增店铺
    int insertShop(Shop shop);

    //更新店铺信息
    int updateShop(Shop shop);

    //通过shopid查询店铺信息
    Shop queryByShopId(Long shopId);

    //分页查询店铺信息
    List<Shop> queryShopList(@Param("shopCondition") Shop ShopCondition, @Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);

    int queryShopCount(@Param("shopCondition") Shop ShopCondition);

}
