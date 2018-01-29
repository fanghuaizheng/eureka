package cn.com.fhz.Controller;

import cn.com.fhz.config.Config;
import cn.com.fhz.entity.AdEntity;
import cn.com.fhz.entity.TUmpCmsContent;
import cn.com.fhz.service.AdService;
import cn.com.fhz.service.TUmpContentService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
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


    @RequestMapping("addAll")
    public Object insertAllContent2Index() throws Exception {

        List<AdEntity> contents = adService.finaAll();

        List<String> dataList = new ArrayList<>();
        ServiceInstance instance = loadBalancerClient.choose("elasticsearch-client");

        String url = "http://"+instance.getHost()+":"+instance.getPort()+config.addIndex+"?";
        String data = JSONObject.toJSONString(contents.get(0));
        url = url.concat("id="+contents.get(0).getId().toString()+"&data="+123+"&clazz="+AdEntity.class);
//        for (AdEntity content: contents
//             ) {
//
//            String data = JSONObject.toJSONString(content);
//            url = url.concat("dataList=").concat(URLEncoder.encode(data,"UTF-8")).concat("&");
////            dataList.add(URLEncoder.encode(data,"UTF-8"));
//            logger.info("获取的数据{}"+data);
//        }
//        url = url.concat("clazz=").concat(AdEntity.class.getName());
        //开始调用spring-cloud的微服务

        logger.info("请求的url路径是:\t"+url);
        return restTemplate.getForObject(url,Object.class);
    }


}
