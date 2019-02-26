package com.furao.shiroProject.bean;

public class User {
    private String userName = "zhangsan";
    private String password = "61a310016ddcf3312aa004dc714f976f";
    private String salt = "MARK";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
