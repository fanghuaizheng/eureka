package cn.com.fhz.component;

import cn.com.fhz.config.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by woni on 18/1/27.
 * elasticsearch 组建类
 */
@Component
public class ElasticsearchComponent {

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchComponent.class);

    @Autowired
    Config config;

    public static void closeResurse(RestHighLevelClient client){

        if (client!=null){
            try {
                client.close();
            } catch (IOException e) {
                logger.info("资源关闭异常");
                e.printStackTrace();
            }
        }
    }

    public RestHighLevelClient getClient(){
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(config.url,config.port,config.schme)));
    }

}
