package cn.com.fhz.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端 elasticsearch 的控制器
 * Created by hzfang on 2018/1/26.
 */
@RestController
public class ElasticSearchController {


    @RequestMapping("addIndex")
    public Object addIndex(String obj,Class clazz){

        //转化后的表
        Object object = JSONObject.parseObject(obj, clazz);
        String type = clazz.getSimpleName();


        return null;

    }


}
