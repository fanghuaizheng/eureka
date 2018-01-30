package cn.com.fhz.entity;

/**
 * Created by hzfang on 2018/1/30.
 *广告查询返回的实体类
 */
public class SearchAdEntity extends AdEntity {

    //用于保存高亮的属性
    private String[] highLightValue;

    public String[] getHighLightValue() {
        return highLightValue;
    }

    public void setHighLightValue(String[] highLightValue) {
        this.highLightValue = highLightValue;
    }
}
