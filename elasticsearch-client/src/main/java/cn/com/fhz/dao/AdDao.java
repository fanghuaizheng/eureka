package cn.com.fhz.dao;

import cn.com.fhz.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by woni on 18/1/29.
 */
@Repository
public interface AdDao extends JpaRepository<AdEntity,Integer>,PagingAndSortingRepository<AdEntity,Integer>{
}
