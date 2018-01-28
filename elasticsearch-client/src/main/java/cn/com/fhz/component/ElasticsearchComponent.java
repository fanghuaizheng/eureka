package cn.com.fhz.component;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by woni on 18/1/27.
 * elasticsearch 组建类
 */
public class ElasticsearchComponent {

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchComponent.class);


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

}
