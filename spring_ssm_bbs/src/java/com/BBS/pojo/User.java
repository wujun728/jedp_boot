package com.BBS.pojo;

import com.BBS.pojo.common.commonEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zlq on 2018/7/19.
 * @info 用户表
 */
@Entity
@Table(name = "b_user")
public class User extends commonEntity {


    private String roleid;  //角色id
    private String sex;     //性别
    private String username;    //用户名
    private String loginname;   //登录名
    private String password;    //密码
    private String signature;   // 用户签名
    private String phone;       //手机号
    private String email;       //邮箱
    private String radate;
    private String status;      //状态
    private int del_flag;      //删除标志0未删除，1删除
    private String joindate;    //加入时间
    private String place;       //城市
    private String picPath;    //头像相片路径
    private Integer isCheckIn;   //今日是否签到 0表示否，1表示已签到


    @Column(name = "role_id")
    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "login_name")
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "radate")
    public String getRadate() {
        return radate;
    }

    public void setRadate(String radate) {
        this.radate = radate;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String emali) {
        this.email = emali;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "del_flag")
    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }
    @Column(name = "signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    @Column(name = "join_date")
    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "pic_path")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(name = "is_check_in")
    public Integer getIsCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(Integer isCheckIn) {
        this.isCheckIn = isCheckIn;
    }
}
