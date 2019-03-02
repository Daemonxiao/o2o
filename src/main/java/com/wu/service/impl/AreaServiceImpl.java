package com.wu.service.impl;

import com.wu.dao.AreaDao;
import com.wu.entity.Area;
import com.wu.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {

        return areaDao.queryArea();
    }
}
