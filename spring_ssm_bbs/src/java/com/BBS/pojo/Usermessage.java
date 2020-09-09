package com.BBS.pojo;

import com.BBS.pojo.common.commonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dyl on 2019/4/10.
 * 用户消息记录表
 */

@Entity
@Table(name ="b_usermessge")
public class Usermessage extends commonEntity {


    private String userid;
    private String author;
    private String content;

    private String status;
    private String del_flag;

    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    @Column(name = "author", nullable = false, length = 32)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Column(name = "content", nullable = false )
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
//    @Column(name = "create_time", nullable = false, length = 32)
//    public String getCreate_time() {
//        return create_time;
//    }
//
//    public void setCreate_time(String create_time) {
//        this.create_time = create_time;
//    }
    @Column(name = "status", nullable = false, length = 5)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "del_flag", nullable = false, length = 5)
    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }
}
