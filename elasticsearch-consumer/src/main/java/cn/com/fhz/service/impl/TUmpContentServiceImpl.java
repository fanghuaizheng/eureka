package cn.com.fhz.service.impl;

import cn.com.fhz.dao.TUmpCmsContentDAO;
import cn.com.fhz.entity.TUmpCmsContent;
import cn.com.fhz.service.TUmpContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzfang on 2018/1/29.
 */
@Service
public class TUmpContentServiceImpl implements TUmpContentService {

    @Autowired
    TUmpCmsContentDAO umpCmsContentDAO;

    @Override
    public List<TUmpCmsContent> findAll() {
        return umpCmsContentDAO.findAll();
    }
}
