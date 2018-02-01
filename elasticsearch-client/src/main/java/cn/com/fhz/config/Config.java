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
    public String url;


    @Value("${elasticsearch.port:9200}")
    public Integer port;

    @Value("${elasticsearch.index}")
    public String index;

    @Value("${elasticsearch.schme:http}")
    public String schme;

    @Value("${security.user.name}")
    public String name;

    @Value("${security.user.password}")
    public String password;

}
