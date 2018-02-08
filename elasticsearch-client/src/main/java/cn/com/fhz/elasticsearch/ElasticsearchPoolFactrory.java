package cn.com.fhz.elasticsearch;

import cn.com.fhz.config.Config;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by hzfang on 2018/1/26.
 * 后期准备做连接池设置
 */
@Component
public class ElasticsearchPoolFactrory implements PooledObjectFactory<RestHighLevelClient> {


    @Autowired
    Config config;

    /**
     * 生产一个连接对象
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<RestHighLevelClient> makeObject() throws Exception {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(config.url, config.port, config.schme)));

         return new DefaultPooledObject<RestHighLevelClient>(client);

    }

    @Override
    public void destroyObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {

        closeResource(pooledObject.getObject());



    }

    private void closeResource(RestHighLevelClient client) throws IOException {
        client.close();
    }

    /**
     * 功能描述：判断资源对象是否有效，有效返回 true，无效返回 false
     *
     * 什么时候会调用此方法
     * 1：从资源池中获取资源的时候，参数 testOnBorrow 或者 testOnCreate 中有一个 配置 为 true 时，则调用  factory.validateObject() 方法
     * 2：将资源返还给资源池的时候，参数 testOnReturn，配置为 true 时，调用此方法
     * 3：资源回收线程，回收资源的时候，参数 testWhileIdle，配置为 true 时，调用此方法
     */
    @Override
    public boolean validateObject(PooledObject<RestHighLevelClient> pooledObject) {
        return true;
    }

    /**
     * 功能描述：激活资源对象
     *
     * 什么时候会调用此方法
     * 1：从资源池中获取资源的时候
     * 2：资源回收线程，回收资源的时候，根据配置的 testWhileIdle 参数，判断 是否执行 factory.activateObject()方法，true 执行，false 不执行
     * @param arg0
     */
    @Override
    public void activateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {

    }

    /**
     * 功能描述：钝化资源对象
     *
     * 什么时候会调用此方法
     * 1：将资源返还给资源池时，调用此方法。
     */
    @Override
    public void passivateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {


    }


    public static void main(String[] args) {



    }

}
