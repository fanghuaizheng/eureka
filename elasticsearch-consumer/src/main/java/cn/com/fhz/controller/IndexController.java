package cn.com.fhz.controller;

import cn.com.fhz.config.Config;
import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.searchEntity.SearchResquestVO;
import cn.com.fhz.service.AdService;
import cn.com.fhz.utils.CommonUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    AdService adService;

    @Autowired
    Config config;

    @GetMapping("add")
    public Integer add(Integer a,String b){
        ServiceInstance instance = loadBalancerClient.choose("elasticsearch-client");
        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/elasticsearch/add?a="+a+"&b="+b;
        System.out.println(url);
        return restTemplate.getForObject(url,Integer.class);
    }


    @GetMapping("addAll")
    public Object insertAllContent2Index() throws Exception {

        List<AdEntity> contents = adService.findAll();

        List<String> dataList = new ArrayList<>();
        ServiceInstance instance = loadBalancerClient.choose("elasticsearch-client");

        String url = "http://"+instance.getHost()+":"+instance.getPort()+config.addIndexs;
//        String data = JSONObject.toJSONString(contents.get(0));
        for (AdEntity content: contents
             ) {

            String data = JSONObject.toJSONString(content);
            dataList.add(data);
            logger.info("获取的数据{}"+data);
        }
        //开始调用spring-cloud的微服务

        logger.info("请求的url路径是:\t"+url);

        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();

//        params.add("id",contents.get(0).getId());
        params.add("dataList",JSONObject.toJSON(dataList));
        params.add("clazz",AdEntity.class.getName());

      return restTemplate.postForEntity(url,params,Object.class);
    }



}
