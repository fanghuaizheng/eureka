package cn.com.fhz.dao;

import cn.com.fhz.entity.TUmpCmsContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hzfang on 2018/1/29.
 */
@Repository
public interface TUmpCmsContentDAO extends JpaRepository<TUmpCmsContent,String>,PagingAndSortingRepository<TUmpCmsContent,String>{

}
