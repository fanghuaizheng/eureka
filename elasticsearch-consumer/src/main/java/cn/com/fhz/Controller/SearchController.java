package cn.com.fhz.Controller;

import cn.com.fhz.config.Config;
import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.entity.SearchAdEntity;
import cn.com.fhz.searchEntity.SearchResquestVO;
import cn.com.fhz.utils.CommonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hzfang on 2018/1/29.
 * 搜索的入口
 */
@RestController
public class SearchController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    Config config;

    @RequestMapping("search")
    public Object search(String key,String value){

        SearchResquestVO vo = new SearchResquestVO();

        //设置必要的属性
        vo.setField(key);
        vo.setValue(value);
        vo.setType(AdEntity.class.getSimpleName());
        vo.setResponseClazz(SearchAdEntity.class);


        String url = CommonUtils.getUrl4Client(loadBalancerClient,config.client,config.searchUrl);

        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();

        params.add("searchRequestVO", JSONObject.toJSON(vo));

        return restTemplate.postForEntity(url,params,Object.class);


    }



}
