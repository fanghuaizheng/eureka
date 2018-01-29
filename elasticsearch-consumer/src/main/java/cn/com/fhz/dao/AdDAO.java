package cn.com.fhz.dao;

import cn.com.fhz.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hzfang on 2018/1/29.
 */
@Repository
public interface AdDAO extends JpaRepository<AdEntity,Integer>,PagingAndSortingRepository<AdEntity,Integer> {
}
