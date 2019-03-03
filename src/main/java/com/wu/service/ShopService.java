package com.wu.service;

import com.wu.dto.ShopExecution;
import com.wu.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ShopService {
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

    Shop getByShopId(long shopId);

    ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg);
}
