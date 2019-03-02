package com.wu.service;

import com.wu.BastTest;
import com.wu.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BastTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList.get(0).getAreaName());
    }
}
