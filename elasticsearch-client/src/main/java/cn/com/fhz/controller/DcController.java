package cn.com.fhz.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by woni on 18/1/25.
 */
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("dc")
    public String dc(){
//        String service = "Service:"+discoveryClient.
                String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }




}
