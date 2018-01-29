package cn.com.fhz.Controller;

import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.entity.TUmpCmsContent;
import cn.com.fhz.service.AdService;
import cn.com.fhz.service.TUmpContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hzfang on 2018/1/29.
 * 索引创建控制器
 */
@RestController
@RequestMapping("index")
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    AdService adService;

    @RequestMapping("addAll")
    public Object insertAllContent2Index(){

        List<AdEntity> contents = adService.finaAll();

        for (AdEntity content: contents
             ) {
            logger.info("获取的数据{}"+content);
        }


        return null;
    }


}
