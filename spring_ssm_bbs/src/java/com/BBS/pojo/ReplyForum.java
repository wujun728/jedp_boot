package com.BBS.pojo;

import com.BBS.pojo.common.commonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zlq on 2019/1/28.
 */
@Entity
@Table(name = "b_replyforum")
public class ReplyForum extends commonEntity {



    private String forumid;    //帖子主ID
    private String title ;      //帖子标题
    private String content;     //帖子内容
    private String picPath;     //帖子图片
    private String author ;     //帖子作者
    private String replyauthor; //回复人名称
    private String makeTime ;   //创建时间
    private String makeDate ;   //创建日期
    private int reward;         //帖子悬赏分数
    private String bbstype;;       //帖子主题
    private int readTimes;      //帖子阅读人数
    private int replyTimes;     //帖子回复人数
    private int supportTimes;  //帖子点赞人数
    private String remark;      //备注
    private int status;         //帖子状态
    private int del_flag;       //删除标志

    @Column(name = "forum_id",length = 32,nullable = false)
    public String getForumid() {
        return forumid;
    }

    public void setForumid(String forumid) {
        this.forumid = forumid;
    }
    @Column(name = "title",length = 32)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "content",length = 32)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "pic_path",length = 32)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    @Column(name = "author",length = 32)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Column(name = "reply_author",length = 32)
    public String getReplyauthor() {
        return replyauthor;
    }

    public void setReplyauthor(String replyauthor) {
        this.replyauthor = replyauthor;
    }
    @Column(name = "make_time",length = 32)
    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }
    @Column(name = "make_date",length = 32)
    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }
    @Column(name = "reward",length = 32)
    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
    @Column(name = "bbs_type",length = 32)
    public String getBbstype() {
        return bbstype;
    }

    public void setBbstype(String bbstype) {
        this.bbstype = bbstype;
    }
    @Column(name = "read_tmes",length = 32)
    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }
    @Column(name = "reply_times",length = 32)
    public int getReplyTimes() {
        return replyTimes;
    }

    public void setReplyTimes(int replyTimes) {
        this.replyTimes = replyTimes;
    }
    @Column(name = "support_times",length = 32)
    public int getSupportTimes() {
        return supportTimes;
    }

    public void setSupportTimes(int supportTimes) {
        this.supportTimes = supportTimes;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name = "status",length = 2)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Column(name = "del_flag",length = 2)
    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }
}
