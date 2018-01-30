package cn.com.fhz.searchEntity;

/**
 * Created by hzfang on 2018/1/30.
 */
public enum  MySearchResult {

    SUCCESS(200,"成功"),ERROR(500,"发生错误");

    private Integer code;

    private String msg;

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

    MySearchResult() {
    }
    MySearchResult(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
