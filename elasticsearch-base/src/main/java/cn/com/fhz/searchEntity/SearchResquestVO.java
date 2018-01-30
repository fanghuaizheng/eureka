package cn.com.fhz.searchEntity;

/**
 * Created by hzfang on 2018/1/30.
 * 搜索请求实体封装类
 */
public class SearchResquestVO {

    private Integer from;//开始条数

    private Integer size;//查询跨度

    private String type;//索引所在的type

    private String field;//查询的域

    private String value;//查询的值

    private String sortField;//排序的域值

    private Boolean sort;//升序还是降序

    private Class responseClazz;//返回的实体类类

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class getResponseClazz() {
        return responseClazz;
    }

    public void setResponseClazz(Class responseClazz) {
        this.responseClazz = responseClazz;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Boolean getSort() {
        return sort;
    }

    public void setSort(Boolean sort) {
        this.sort = sort;
    }
}
