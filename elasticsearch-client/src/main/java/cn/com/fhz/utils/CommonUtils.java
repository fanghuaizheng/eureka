package cn.com.fhz.utils;

import cn.com.fhz.constant.Constant;
import cn.com.fhz.searchEntity.MySearchResult;
import cn.com.fhz.searchEntity.SearchResponseVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 组装客户端返回值
     * @param responseVO
     * @param result
     */
    public void putValue2Result(SearchResponseVO responseVO, MySearchResult result,List<Object> data){
        responseVO.setCode(result.getCode());
        responseVO.setMsg(result.getMsg());
        responseVO.setData(data);
    }



}
