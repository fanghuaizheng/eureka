package cn.com.fhz.service.impl;

import cn.com.fhz.dao.AdDAO;
import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.service.AdService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzfang on 2018/1/29.
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdDAO adDAO;

    @Override
    public List<AdEntity> finaAll() {
        return adDAO.findAll();
    }
}
