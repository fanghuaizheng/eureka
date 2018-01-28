package cn.com.fhz.controller;

import cn.com.fhz.config.Config;
import cn.com.fhz.constant.Constant;
import cn.com.fhz.utils.CommonUtils;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端 elasticsearch 的控制器
 * Created by hzfang on 2018/1/26.
 */
@RestController
public class ElasticSearchController {

    private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);


    @Autowired
    Config config;

    @Autowired
    CommonUtils commonUtils;

    /**
     *单个索引增加
     * @param id 操作的索引id
     * @param data 操作的索引数据，以json格式传
     * @param clazz 操作的索引类型
     * @return
     */
    @RequestMapping("addIndex")
    public Object addIndex(String id,String data,Class clazz){

        //存储操作的结果
        JSONObject result = new JSONObject();
        try {
           //转化后的表
           String type = clazz.getSimpleName();



           RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                   new HttpHost(config.index, config.port, config.schme)));


           IndexRequest indexRequest = null;
           if (StringUtils.isNotBlank(id)){
               indexRequest = new IndexRequest(config.index,type,id);
           }else {
               indexRequest = new IndexRequest(config.index, type);

           }

            indexRequest.source(data, XContentType.JSON);

           IndexResponse indexResponse = client.index(indexRequest);
           if (indexResponse.getResult()== DocWriteResponse.Result.CREATED){

               logger.info("创建成功");

               commonUtils.putValue2Result(result,Constant.CREATE);


           }else if (indexResponse.getResult()==DocWriteResponse.Result.UPDATED){
               logger.info("更新成功");

               commonUtils.putValue2Result(result,Constant.UPDATE);

           }

       }catch (Exception e){
           e.printStackTrace();
           commonUtils.putValue2Result(result,Constant.ERROR);

       }finally {

           return  result;

       }
    }

    @RequestMapping("addIndexs")
    public Object addIndex(List<String> dataList,Class clazz){


        return  null;
    }

}
