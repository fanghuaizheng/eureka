package cn.com.fhz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hzfang on 2018/1/29.
 */
@Entity
@Table(name = "t_ad")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "user_id",length = 11)
    private Integer userId;

    @Column(name = "type",length = 50)
    private String type;

    @Column(name = "ad_category_ids",length = 100)
    private String adCategoryIds;

    @Column(name = "width",length = 4)
    private Integer width;

    @Column(name = "height",length = 4)
    private Integer height;

    @Column(name = "img_url",length = 100)
    private String imgUrl;

    @Column(name = "isclick",length = 2)
    private Integer isclick;

    @Column(name = "click_url",length = 100)
    private String clickUrl;

    @Column(name = "text",length = 100)
    private String text;

    @Column(name = "score",length = 11)
    private Integer score;

    @Column(name = "status",length = 2)
    private Integer status;

    @Column(name = "key_words",length = 200)
    private String keyWords;

    @Column(name = "reject_reason",length = 200)
    private String rejectReason;

    @Column(name = "remark",length = 200)
    private String remark;

    @Column(name = "createtime")
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdCategoryIds() {
        return adCategoryIds;
    }

    public void setAdCategoryIds(String adCategoryIds) {
        this.adCategoryIds = adCategoryIds;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIsclick() {
        return isclick;
    }

    public void setIsclick(Integer isclick) {
        this.isclick = isclick;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
