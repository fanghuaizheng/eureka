package cn.com.fhz.controller;

import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by woni on 18/1/25.
 */
@Api(value = "测试类",description = "测试类")
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @ApiOperation(value = "获取注册服务器上面所有的服务")
    @GetMapping("dc")
    public String dc(){
//        String service = "Service:"+discoveryClient.
                String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @ApiOperation(value="两个数据增加",notes = "两个数字增加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a",value = "加数第一个",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "b",value = "加数第二个",required = true,dataType = "Integer",paramType = "query"),

    })
    @GetMapping("add")
    public Integer add(@RequestParam("a")Integer a,@RequestParam Integer b){

        return a+b;

    }


    //获取传参的类名称
    @ApiOperation(value = "获取传如类对象的名称",notes = "获取传如类对象的名称")
    @ApiImplicitParam(name = "clazz",value = "传入类对象",required = true,dataType = "Class")
    @GetMapping("clazz")
    public String testClazz(@RequestParam("clazz") Class clazz){
        return clazz.getSimpleName();
    }

    @ApiOperation(value = "测试post方法",notes = "测试post方法传参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a",value = "第一个参数",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "b",value = "第一个参数",required = true,dataType = "Integer",paramType = "query")

    })
    @PostMapping("postAdd")
    public Integer postAdd(@RequestParam("a") Integer a,@RequestParam("b")Integer b){

        return a+b;

    }


}
