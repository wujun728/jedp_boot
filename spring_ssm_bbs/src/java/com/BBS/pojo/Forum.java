package com.BBS.pojo;

import com.BBS.pojo.common.commonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zlq on 2018/9/6.
 */
@Entity
@Table(name = "b_forum")
public class Forum   implements Serializable {


    private String forumid; //帖子ID
    private String mainid;    //帖子主人ID
    private String authorPath;  //作者头像
    private String title ;      //帖子标题
    private String content;     //帖子内容
    private String picPath;     //帖子图片
    private String author ;     //帖子作者
    private String makeTime ;   //创建时间
    private String makeDate ;   //创建日期
    private int reward;         //帖子悬赏分数
    private String bbsType;       //帖子主题
    private int readTimes;      //帖子阅读人数
    private int replyTimes;     //帖子回复人数
    private String remark;      //备注
    private int status;         //帖子状态
    private int del_flag;       //删除标志
    private int top_status;  //置顶状态
    private int delicate_status;     //精贴状态


    @Id
    @GeneratedValue(
            generator = "paymentableGenerator"
    )
    @GenericGenerator(
            name = "paymentableGenerator",
            strategy = "uuid"
    )
    @Column(name = "forum_id",length = 32, nullable = false)
    public String getForumid() {
        return forumid;
    }

    public void setForumid(String forumid) {
        this.forumid = forumid;
    }
    @Column(name = "main_id")
    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    @Column(name ="author_path" )
    public String getAuthorPath() {
        return authorPath;
    }

    public void setAuthorPath(String authorPath) {
        this.authorPath = authorPath;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "pic_path")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Column(name = "make_time")
    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }
    @Column(name = "make_date")
    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }
    @Column(name = "bbs_type")
    public String getBbsType() {
        return bbsType;
    }

    public void setBbsType(String bbsType) {
        this.bbsType = bbsType;
    }
    @Column(name = "read_times")
    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }
    @Column(name = "reply_times")
    public int getReplyTimes() {
        return replyTimes;
    }

    public void setReplyTimes(int replyTimes) {
        this.replyTimes = replyTimes;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name = "reward")
    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Column(name = "del_flag")
    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }
    @Column(name = "top_status")
    public int getTop_status() {
        return top_status;
    }

    public void setTop_status(int top_status) {
        this.top_status = top_status;
    }
    @Column(name = "delicate_status")
    public int getDelicate_status() {
        return delicate_status;
    }

    public void setDelicate_status(int delicate_status) {
        this.delicate_status = delicate_status;
    }
}
