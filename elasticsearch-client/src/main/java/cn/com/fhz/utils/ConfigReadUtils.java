package cn.com.fhz.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hzfang on 2018/2/8.
 * 读取配置文件集合类
 */
public class ConfigReadUtils {

    private static Logger logger = LoggerFactory.getLogger(ConfigReadUtils.class);

    private static final String defaultConfigPath = "application.properties";

    public static Configuration getProperties(String path){
        Configuration prop = null;
        try {

            prop = new PropertiesConfiguration(path);
        } catch (ConfigurationException e) {
//            e.printStackTrace();
            logger.info("当前:\t"+path+"\t没有获取到数据,开始调用默认配置:\t"+defaultConfigPath);
            prop = new PropertiesConfiguration(defaultConfigPath);
        }finally {
            return prop;
        }
    }

    public static void main(String[] args) {

        String path = "/iflytek/config/config.properties";
        try {
            Configuration config = getProperties(path);

            System.out.println(config.getString("elasticsearch.url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
