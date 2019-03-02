package com.wu.service;

import com.wu.dto.ShopExecution;
import com.wu.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}
