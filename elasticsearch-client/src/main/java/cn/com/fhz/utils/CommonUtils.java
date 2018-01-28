package cn.com.fhz.utils;

import cn.com.fhz.constant.Constant;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 普通的一些工具类方法集合
 * Created by woni on 18/1/28.
 */
@Component
public class CommonUtils {

    public void putValue2Result(JSONObject result,Constant constant){
        result.put("code",constant.getCode());
        result.put("msg",constant.getMsg());

    }



}
