package com.repair.lsb.easyrepair.model;

public class UserEntity {
    private int id;
    private String username,phoneNum,userPwd,email,image,address;

    public UserEntity(String phoneNum, String userPwd) {
        this.phoneNum = phoneNum;
        this.userPwd = userPwd;
    }

    public UserEntity(String username, String phoneNum, String userPwd, String email, String image, String address) {
        this.username = username;
        this.phoneNum = phoneNum;
        this.userPwd = userPwd;
        this.email = email;
        this.image = image;
        this.address = address;
    }

    public UserEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
