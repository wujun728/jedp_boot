package com.BBS.service.impl;

import com.BBS.dao.CityDao;
import com.BBS.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dyl on 2018/7/19.
 */
@Service
public class CityserviceImpl implements  CityService {
    @Autowired
    CityDao cityDao;
    public String getCityName(String id) {
        return cityDao.queryById(id);
    }
}
