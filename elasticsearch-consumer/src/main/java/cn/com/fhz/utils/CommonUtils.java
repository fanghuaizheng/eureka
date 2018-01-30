package cn.com.fhz.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 * Created by hzfang on 2018/1/30.
 * 一些小的工具类
 */
public class CommonUtils {

    /**
     * 从客户端获取整个微服务url
     * @param client
     * @param clientName
     * @return
     */
    public static String getUrl4Client(LoadBalancerClient client,String clientName,String url){
        ServiceInstance instance = client.choose(clientName);

        if (instance!=null){
            return "http://"+instance.getHost()+":"+instance.getPort()+url;
        }else return null;

    }

}
