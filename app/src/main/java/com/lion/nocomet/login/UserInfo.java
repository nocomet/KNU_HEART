package com.lion.nocomet.login;

/**
 * Created by 김정섭 on 2015-12-16.
 */
public class UserInfo {

    private String userName;
    private int stuNbr;
    private String major;
    private int regCnt;
    private String grad_suffincy;

    public UserInfo() {    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStuNbr() {
        return stuNbr;
    }

    public void setStuNbr(int stuNbr) {
        this.stuNbr = stuNbr;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getRegCnt() {
        return regCnt;
    }

    public void setRegCnt(int regCnt) {
        this.regCnt = regCnt;
    }

    public String getGrad_suffincy() {
        return grad_suffincy;
    }

    public void setGrad_suffincy(String grad_suffincy) {
        this.grad_suffincy = grad_suffincy;
    }
}
