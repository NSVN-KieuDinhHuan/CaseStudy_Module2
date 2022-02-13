package com.company.model;

import java.io.Serializable;

public class User  implements Serializable {
    private String loginName;
    private String passWord;
    private String role;
    private String fullName;
    private String japaneseLever;

    public User(String loginName, String passWord,String role) {
        this.loginName = loginName;
        this.passWord = passWord;
        this.role=role;
    }

    public User(String loginName, String passWord, String role, String fullName, String japaneseLever) {
        this.loginName = loginName;
        this.passWord = passWord;
        this.role = role;
        this.fullName = fullName;
        this.japaneseLever = japaneseLever;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJapaneseLever() {
        return japaneseLever;
    }

    public void setJapaneseLever(String japaneseLever) {
        this.japaneseLever = japaneseLever;
    }

    @Override
    public String toString() {
        return "role='" + role + '\'' +
                ", Name='" + fullName + '\'' +
                ", japaneseLever='" + japaneseLever + '\'' ;
    }
}