package cn.com.fhz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件
 * Created by woni on 18/1/27.
 */
@Configuration
public class Config {

    @Value("${elasticsearch.url:127.0.0.1}")
    public String index;

    @Value("${elasticsearch.port:9200}")
    public Integer port;

    @Value("${elasticsearch.schme}")
    public String schme;

}
