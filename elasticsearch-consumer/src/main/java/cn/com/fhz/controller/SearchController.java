package cn.com.fhz.controller;

import cn.com.fhz.config.Config;
import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.searchEntity.SearchResquestVO;
import cn.com.fhz.utils.CommonUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hzfang on 2018/1/29.
 * 搜索的入口
 */
@Api(value = "搜索的总入口",description = "搜索的总入口")
@RestController
public class SearchController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    Config config;


    @ApiOperation(value = "按照识丁的条件，搜索出结果",notes = "按照指定的条件，查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索的字段",name = "key",required = true,paramType = "query",
            dataType = "String"),
            @ApiImplicitParam(value = "搜索字段的值",name = "value",required = true,paramType = "query",
            dataType = "String")
    })
    @GetMapping("search")
    public Object search(@RequestParam("key") String key,
                         @RequestParam("value") String value){

        SearchResquestVO vo = new SearchResquestVO();

        //设置必要的属性
        vo.setField(key);
        vo.setValue(value);
        vo.setType(AdEntity.class.getSimpleName());
//        vo.setResponseClazz(SearchAdEntity.class);


        String url = CommonUtils.getUrl4Client(loadBalancerClient,config.client,config.searchUrl);

        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();

        params.add("searchRequestVO", JSONObject.toJSON(vo));

        return restTemplate.postForEntity(url,params,Object.class);


    }



}
