package cn.com.fhz.elasticsearch;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by hzfang on 2018/2/9.
 * 连接工厂
 *
 */
public class ElasticsearchConnentFactroy {

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchConnentFactroy.class);


   private static ElasticsearchPoolFactrory elasticsearchPoolFactrory = new ElasticsearchPoolFactrory();

   private static ElasticsearchPoolConfig elasticsearchPoolConfig = new ElasticsearchPoolConfig();

    /**
     * 获取默认的连接
     * @return
     */
    public static RestHighLevelClient getClient(){

        logger.info("根据默认的配置文件，获取连接客户连接");
        RestHighLevelClient client = null;

        GenericObjectPool<RestHighLevelClient> pool = new GenericObjectPool<>(elasticsearchPoolFactrory, elasticsearchPoolConfig.getConfig());

        try {
            client =  pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return client;
        }

    }


    /**
     * 指定配置文件的客户端
     * @param path
     * @return
     */
    public static RestHighLevelClient getClient(String path){

        logger.info("根据"+path+"地址下的配置文件，获取客户端连接");

        elasticsearchPoolConfig = new ElasticsearchPoolConfig(path);

        elasticsearchPoolFactrory = new ElasticsearchPoolFactrory(elasticsearchPoolConfig);

        RestHighLevelClient client = null;

        GenericObjectPool<RestHighLevelClient> pool = new GenericObjectPool<>(elasticsearchPoolFactrory, elasticsearchPoolConfig.getConfig());

        try {
            client =  pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return client;
        }

    }

    public static void closeClient(RestHighLevelClient client){

        if (client!=null){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) {



        RestHighLevelClient client = getClient();

        Integer maxTotal = elasticsearchPoolConfig.getMaxTotal();

        System.out.println("maxTotal\t"+maxTotal);

        logger.info("开始获取指定配置文件的客户端连接");

        String path = "application-elasticsearch.properties";

        RestHighLevelClient levelClient = getClient(path);

        Integer total = elasticsearchPoolConfig.getMaxTotal();

        logger.info("获取配置文件中maxTotal:\t"+total);

    }


}
