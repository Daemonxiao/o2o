package com.wu.dao;

import com.wu.BastTest;
import com.wu.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AreaDaoTest extends BastTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        System.out.println(areaList.get(0).getAreaName());
    }
}
