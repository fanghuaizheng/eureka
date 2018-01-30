package cn.com.fhz.searchEntity;

import java.util.List;

/**
 * Created by hzfang on 2018/1/30.
 * 搜索返回的结果
 */
public class SearchResponseVO {

    private Integer code;//返回结果code

    private String msg;//返回结果信息

    private List<Object> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
