package cn.com.fhz.service;

import cn.com.fhz.entity.AdEntity;

import java.util.List;

/**
 * Created by woni on 18/1/29.
 */
public interface AdService {
    List<AdEntity> findAll();
}
