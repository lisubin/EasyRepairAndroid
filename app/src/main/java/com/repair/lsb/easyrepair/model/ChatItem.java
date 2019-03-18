package com.repair.lsb.easyrepair.model;

import java.io.Serializable;
import java.util.Date;

public class ChatItem implements Serializable {
    private int id,userId,merId;
    private String lastText,name,userAvatar,merAvatar;
    private String lastTime;

    public ChatItem(int id, int userId, int merId, String lastText, String lastTime) {
        this.id = id;
        this.userId = userId;
        this.merId = merId;
        this.lastText = lastText;
        this.lastTime = lastTime;
    }

    public ChatItem(int id, int userId, int merId,String name, String lastText, String userAvatar,String merAvatar, String lastTime) {
        this.id = id;
        this.userId = userId;
        this.merId = merId;
        this.lastText = lastText;
        this.name = name;
        this.userAvatar = userAvatar;
        this.merAvatar = merAvatar;
        this.lastTime = lastTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMerId() {
        return merId;
    }

    public void setMerId(int merId) {
        this.merId = merId;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getMerAvatar() {
        return merAvatar;
    }

    public void setMerAvatar(String merAvatar) {
        this.merAvatar = merAvatar;
    }
}
