package cn.springmvc.mongo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Vincent.wang
 *
 */
@Document(collection = "d_open_survey")
public class OpenSurvey implements BaseEntity<String> {

    private static final long serialVersionUID = 1538192110129669137L;

    @Id
    private String id;

    @Field("f_id")
    private String fid;

    // 调查名字
    @Field("f_name")
    private String name;

    // 调查详细描述
    @Field("f_description")
    private String description;

    // 创建人id
    @Field("f_uid")
    private String uid;

    // 创建时间
    @Field("f_createtime")
    private Date createtime;

    // 是否分享报告数据
    @Field("f_allowreport")
    private Integer allowreport;

    // 分类
    @Field("f_type")
    private Integer type;

    // 分类名
    @Field("f_typename")
    private String typename;

    // 当前调查样本量，默认5000
    @Field("f_samplecount")
    private Integer samplecount;

    // 当前已做过调查人数
    @Field("f_samplesum")
    private Integer samplesum;

    // 访问人数
    @Field("f_viewersum")
    private Integer viewersum;

    // 自定义链接
    @Field("f_surveyurl")
    private String surveyurl;

    // 页数
    @Field("f_pagecount")
    private Integer pagecount;

    // 问题数
    @Field("f_questioncount")
    private Integer questioncount;

    // 热门样本数
    @Field("f_usersurveycount")
    private Integer usersurveycount;

    // 是否显示
    @Field("f_show")
    private Integer show;

    // 标签
    @Field("f_tag")
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getAllowreport() {
        return allowreport;
    }

    public void setAllowreport(Integer allowreport) {
        this.allowreport = allowreport;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getSamplecount() {
        return samplecount;
    }

    public void setSamplecount(Integer samplecount) {
        this.samplecount = samplecount;
    }

    public Integer getSamplesum() {
        return samplesum;
    }

    public void setSamplesum(Integer samplesum) {
        this.samplesum = samplesum;
    }

    public Integer getViewersum() {
        return viewersum;
    }

    public void setViewersum(Integer viewersum) {
        this.viewersum = viewersum;
    }

    public String getSurveyurl() {
        return surveyurl;
    }

    public void setSurveyurl(String surveyurl) {
        this.surveyurl = surveyurl;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    public Integer getQuestioncount() {
        return questioncount;
    }

    public void setQuestioncount(Integer questioncount) {
        this.questioncount = questioncount;
    }

    public Integer getUsersurveycount() {
        return usersurveycount;
    }

    public void setUsersurveycount(Integer usersurveycount) {
        this.usersurveycount = usersurveycount;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
