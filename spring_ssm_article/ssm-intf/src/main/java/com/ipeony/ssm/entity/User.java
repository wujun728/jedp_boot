/**
 * CCC 2009-2013 All Rights Reserved.
 */
package com.ipeony.ssm.entity;

/**
 * 
 * 功能描述： 用户实体类
 * @author 作者 13071472
 * @version 1.0.0
 * @date 2013年11月7日 上午8:59:03
 */

public class User {
    private String id;

    private String username;

    private String password;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email="
                + email + "]";
    }

}
