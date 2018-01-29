package cn.com.fhz.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hzfang on 2018/1/29.
 */
@Entity
@Table(name="T_UMP_CMS_CONTENT")
public class TUmpCmsContent{
    public TUmpCmsContent(){

    }
    /**
     *
     */
    @Id
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    /**
     *创建者姓名
     */
    @Column(name = "AUTHOR", length = 32)
    private String author;
    /**
     *关键词
     */
    @Column(name = "KEYWORDS", length = 400)
    private String keywords;
    /**
     *标题
     */
    @Column(name = "TITLE", nullable = false, length = 400)
    private String title;
    /**
     *内容
     */
    @Lob
    private String content;
    /**
     *是否热点 Y是 N否
     */
    @Column(name = "ISHOT", length = 1)
    private String ishot;
    /**
     *有效开始时间
     */
    @Column(name = "BEGIN_DATE", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginDate;
    /**
     *有效结束时间
     */
    @Column(name = "END_DATE", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    /**
     *栏目id
     */
    @Column(name = "MID", nullable = false, length = 32)
    private String mid;
    /**
     *简介
     */
    @Column(name = "INTRODUCTION", length = 400)
    private String introduction;
    /**
     *内容模板
     */
    @Column(name = "TEMPLATE_PATH", length = 400)
    private String templatePath;
    /**
     *资源(图片)URL
     */
    @Column(name = "RES_URL", length = 200)
    private String resUrl;
    /**
     *打开方式 _target,_blank,_self
     */
    @Column(name = "TARGET", length = 20)
    private String target;
    /**
     *创建人账号
     */
    @Column(name = "CREATE_USER", length = 32)
    private String createUser;
    /**
     *创建时间
     */
    @Column(name = "CREATE_TIME", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     *修改时间
     */
    @Column(name = "MODIFY_TIME", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    /**
     *点击次数
     */
    @Column(name = "CLICKS", length = 12)
    private Long clicks;
    /**
     *评论次数
     */
    @Column(name = "REVIEW_COUNT", length = 12)
    private Long reviewCount;
    /**
     *关注数量
     */
    @Column(name = "ATTENTION_COUNT", length = 12)
    private Long attentionCount;
    /**
     *省
     */
    @Column(name = "PROVINCE", length = 10)
    private String province;
    /**
     *市
     */
    @Column(name = "CITY", length = 20)
    private String city;
    /**
     *区
     */
    @Column(name = "AREA", length = 20)
    private String area;
    /**
     *街道（镇）
     */
    @Column(name = "TOWN", length = 20)
    private String town;
    /**
     *社区编码（小区编码）
     */
    @Column(name = "AREACODE", length = 20)
    private String areacode;
    /**
     *经度
     */
    @Column(name = "POSITION_X", length = 32)
    private String positionX;
    /**
     *纬度
     */
    @Column(name = "POSITION_Y", length = 32)
    private String positionY;
    /**
     *状态  0草稿 1已发布 2取消发布
     */
    @Column(name = "STATE", length = 1)
    private String state;
    /**
     *排序
     */
    @Column(name = "SN", length = 12)
    private Long sn;
    /**
     *信息来源
     */
    @Column(name = "INFO_SOURCE", length = 100)
    private String infoSource;
    /**
     *评分
     */
    @Column(name = "SCORE", length = 3)
    private Long score;
    /**
     *发布时间
     */
    @Column(name = "RELEASE_TIME", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseTime;
    /**
     *置顶时间
     */
    @Column(name = "TOP_TIME", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date topTime;
    /**
     *第二张资源图片
     */
    @Column(name = "SECOND_RES_URL", length = 200)
    private String secondResUrl;
    /**
     *第三张资源图片
     */
    @Column(name = "THIRD_RES_URL", length = 200)
    private String thirdResUrl;
    /**
     *浏览量
     */
    @Column(name = "PAGE_VIEW", length = 12)
    private Long pageView;
    /**
     *
     */
    @Column(name = "THIRD_ID", length = 32)
    private String thirdId;
    /**
     *
     */
    public String getId(){
        return this.id;
    }
    /**
     *
     *@param id
     */
    public void setId(String id){
        this.id=id;
    }

    /**
     *创建者姓名
     */
    public String getAuthor(){
        return this.author;
    }
    /**
     *创建者姓名
     *@param author
     */
    public void setAuthor(String author){
        this.author=author;
    }

    /**
     *关键词
     */
    public String getKeywords(){
        return this.keywords;
    }
    /**
     *关键词
     *@param keywords
     */
    public void setKeywords(String keywords){
        this.keywords=keywords;
    }

    /**
     *标题
     */
    public String getTitle(){
        return this.title;
    }
    /**
     *标题
     *@param title
     */
    public void setTitle(String title){
        this.title=title;
    }

    /**
     *内容
     */
    public String getContent(){
        return this.content;
    }
    /**
     *内容
     *@param content
     */
    public void setContent(String content){
        this.content=content;
    }

    /**
     *是否热点 Y是 N否
     */
    public String getIshot(){
        return this.ishot;
    }
    /**
     *是否热点 Y是 N否
     *@param ishot
     */
    public void setIshot(String ishot){
        this.ishot=ishot;
    }

    /**
     *有效开始时间
     */
    public Date getBeginDate(){
        return this.beginDate;
    }
    /**
     *有效开始时间
     *@param beginDate
     */
    public void setBeginDate(Date beginDate){
        this.beginDate=beginDate;
    }

    /**
     *有效结束时间
     */
    public Date getEndDate(){
        return this.endDate;
    }
    /**
     *有效结束时间
     *@param endDate
     */
    public void setEndDate(Date endDate){
        this.endDate=endDate;
    }

    /**
     *栏目id
     */
    public String getMid(){
        return this.mid;
    }
    /**
     *栏目id
     *@param mid
     */
    public void setMid(String mid){
        this.mid=mid;
    }

    /**
     *简介
     */
    public String getIntroduction(){
        return this.introduction;
    }
    /**
     *简介
     *@param introduction
     */
    public void setIntroduction(String introduction){
        this.introduction=introduction;
    }

    /**
     *内容模板
     */
    public String getTemplatePath(){
        return this.templatePath;
    }
    /**
     *内容模板
     *@param templatePath
     */
    public void setTemplatePath(String templatePath){
        this.templatePath=templatePath;
    }

    /**
     *资源(图片)URL
     */
    public String getResUrl(){
        return this.resUrl;
    }
    /**
     *资源(图片)URL
     *@param resUrl
     */
    public void setResUrl(String resUrl){
        this.resUrl=resUrl;
    }

    /**
     *打开方式 _target,_blank,_self
     */
    public String getTarget(){
        return this.target;
    }
    /**
     *打开方式 _target,_blank,_self
     *@param target
     */
    public void setTarget(String target){
        this.target=target;
    }

    /**
     *创建人账号
     */
    public String getCreateUser(){
        return this.createUser;
    }
    /**
     *创建人账号
     *@param createUser
     */
    public void setCreateUser(String createUser){
        this.createUser=createUser;
    }

    /**
     *创建时间
     */
    public Date getCreateTime(){
        return this.createTime;
    }
    /**
     *创建时间
     *@param createTime
     */
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }

    /**
     *修改时间
     */
    public Date getModifyTime(){
        return this.modifyTime;
    }
    /**
     *修改时间
     *@param modifyTime
     */
    public void setModifyTime(Date modifyTime){
        this.modifyTime=modifyTime;
    }

    /**
     *点击次数
     */
    public Long getClicks(){
        return this.clicks;
    }
    /**
     *点击次数
     *@param clicks
     */
    public void setClicks(Long clicks){
        this.clicks=clicks;
    }

    /**
     *评论次数
     */
    public Long getReviewCount(){
        return this.reviewCount;
    }
    /**
     *评论次数
     *@param reviewCount
     */
    public void setReviewCount(Long reviewCount){
        this.reviewCount=reviewCount;
    }

    /**
     *关注数量
     */
    public Long getAttentionCount(){
        return this.attentionCount;
    }
    /**
     *关注数量
     *@param attentionCount
     */
    public void setAttentionCount(Long attentionCount){
        this.attentionCount=attentionCount;
    }

    /**
     *省
     */
    public String getProvince(){
        return this.province;
    }
    /**
     *省
     *@param province
     */
    public void setProvince(String province){
        this.province=province;
    }

    /**
     *市
     */
    public String getCity(){
        return this.city;
    }
    /**
     *市
     *@param city
     */
    public void setCity(String city){
        this.city=city;
    }

    /**
     *区
     */
    public String getArea(){
        return this.area;
    }
    /**
     *区
     *@param area
     */
    public void setArea(String area){
        this.area=area;
    }

    /**
     *街道（镇）
     */
    public String getTown(){
        return this.town;
    }
    /**
     *街道（镇）
     *@param town
     */
    public void setTown(String town){
        this.town=town;
    }

    /**
     *社区编码（小区编码）
     */
    public String getAreacode(){
        return this.areacode;
    }
    /**
     *社区编码（小区编码）
     *@param areacode
     */
    public void setAreacode(String areacode){
        this.areacode=areacode;
    }

    /**
     *经度
     */
    public String getPositionX(){
        return this.positionX;
    }
    /**
     *经度
     *@param positionX
     */
    public void setPositionX(String positionX){
        this.positionX=positionX;
    }

    /**
     *纬度
     */
    public String getPositionY(){
        return this.positionY;
    }
    /**
     *纬度
     *@param positionY
     */
    public void setPositionY(String positionY){
        this.positionY=positionY;
    }

    /**
     *状态  0草稿 1已发布 2取消发布
     */
    public String getState(){
        return this.state;
    }
    /**
     *状态  0草稿 1已发布 2取消发布
     *@param state
     */
    public void setState(String state){
        this.state=state;
    }

    /**
     *排序
     */
    public Long getSn(){
        return this.sn;
    }
    /**
     *排序
     *@param sn
     */
    public void setSn(Long sn){
        this.sn=sn;
    }

    /**
     *信息来源
     */
    public String getInfoSource(){
        return this.infoSource;
    }
    /**
     *信息来源
     *@param infoSource
     */
    public void setInfoSource(String infoSource){
        this.infoSource=infoSource;
    }

    /**
     *评分
     */
    public Long getScore(){
        return this.score;
    }
    /**
     *评分
     *@param score
     */
    public void setScore(Long score){
        this.score=score;
    }

    /**
     *发布时间
     */
    public Date getReleaseTime(){
        return this.releaseTime;
    }
    /**
     *发布时间
     *@param releaseTime
     */
    public void setReleaseTime(Date releaseTime){
        this.releaseTime=releaseTime;
    }

    /**
     *置顶时间
     */
    public Date getTopTime(){
        return this.topTime;
    }
    /**
     *置顶时间
     *@param topTime
     */
    public void setTopTime(Date topTime){
        this.topTime=topTime;
    }

    /**
     *第二张资源图片
     */
    public String getSecondResUrl(){
        return this.secondResUrl;
    }
    /**
     *第二张资源图片
     *@param secondResUrl
     */
    public void setSecondResUrl(String secondResUrl){
        this.secondResUrl=secondResUrl;
    }

    /**
     *第三张资源图片
     */
    public String getThirdResUrl(){
        return this.thirdResUrl;
    }
    /**
     *第三张资源图片
     *@param thirdResUrl
     */
    public void setThirdResUrl(String thirdResUrl){
        this.thirdResUrl=thirdResUrl;
    }

    /**
     *浏览量
     */
    public Long getPageView(){
        return this.pageView;
    }
    /**
     *浏览量
     *@param pageView
     */
    public void setPageView(Long pageView){
        this.pageView=pageView;
    }

    /**
     *
     */
    public String getThirdId(){
        return this.thirdId;
    }
    /**
     *
     *@param thirdId
     */
    public void setThirdId(String thirdId){
        this.thirdId=thirdId;
    }

}