package cn.com.fhz.service.impl;

import cn.com.fhz.dao.AdDao;
import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by woni on 18/1/29.
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdDao adDao;


    @Override
    public List<AdEntity> findAll() {
        return adDao.findAll();
    }
}
