package com.cheng.shiro.vo;

/**
 * @author chengchenrui
 * @version Id: User.java, v 0.1 2018/6/29 1:04 chengchenrui Exp $$
 */
public class User {

    private String  username;
    private String  password;
    private boolean rememberMe;

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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}