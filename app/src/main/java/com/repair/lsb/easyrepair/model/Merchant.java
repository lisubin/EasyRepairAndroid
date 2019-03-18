package com.repair.lsb.easyrepair.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Merchant implements Serializable {
    private int id;
    private float score;
    private String phone,pwd,type,address,name,image;

    public Merchant(){

    }

    public Merchant(int id, String phone, String pwd, String type, String address, String name, String image ,float score) {
        this.id = id;
        this.score = score;
        this.phone = phone;
        this.pwd = pwd;
        this.type = type;
        this.address = address;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", score=" + score +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
