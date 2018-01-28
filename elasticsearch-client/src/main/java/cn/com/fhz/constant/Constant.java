package cn.com.fhz.constant;

/**
 * Created by woni on 18/1/28.
 */
public enum Constant {

    CREATE(200,"创建成功"),UPDATE(201,"更新成功"),
    BATCH_OP(202,"批操作成功"),
    ERROR(500,"服务器错误");

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

    private Constant(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
