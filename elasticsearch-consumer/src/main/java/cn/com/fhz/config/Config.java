package cn.com.fhz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hzfang on 2018/1/29.
 * 配置文件
 */
@Configuration
public class Config {

    @Value("${elasticsearch.addIndex}")
    public String addIndex;

    @Value("${elasticsearch.addIndexs}")
    public String addIndexs;

}
