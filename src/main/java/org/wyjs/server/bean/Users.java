package org.wyjs.server.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * 用户
 */
public class Users implements Serializable {
    private String uid;
    private Date date;
    private int aid;
    private String type;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "Users{" +
                "uid='" + uid + '\'' +
                ", date=" + date +
                ", aid=" + aid +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
