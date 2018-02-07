package cn.com.fhz.controller;

import cn.com.fhz.entity.AdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by woni on 18/1/25.
 */
@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("consumer")
    public String dc(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("elasticsearch-client");

        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/dc";
        System.out.println(url);

        return restTemplate.getForObject(url,String.class);

    }

//    @GetMapping("add")
//    public Integer add(Integer a,String b){
//        ServiceInstance instance = loadBalancerClient.choose("elasticsearch-client");
//        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/dc/add?a="+a+"&b="+b;
//        System.out.println(url);
//        return restTemplate.getForObject(url,Integer.class);
//    }

    @GetMapping("testClazz")
    public String testClazz(){
        ServiceInstance instance = loadBalancerClient.choose("elasticsearch-client");

        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/clazz?clazz="+ AdEntity.class.getName();

        System.out.println(url);
        return restTemplate.getForObject(url,String.class);

    }

}
