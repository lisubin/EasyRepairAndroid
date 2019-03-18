package com.repair.lsb.easyrepair.model;


public class Chat {
    private int id,userId,merId,type;
    private String time;
    private String content,userAvatar,merAvatar;

    public Chat(int userId, int merId, int type, String time, String content,String avatar) {
        this.userId = userId;
        this.merId = merId;
        this.type = type;
        this.time = time;
        this.content = content;
        this.userAvatar = avatar;
    }



    public Chat(int id, int userId, int merId, int type, String time, String content, String userAvatar, String merAvatar) {
        this.id = id;
        this.userId = userId;
        this.merId = merId;
        this.type = type;
        this.time = time;
        this.content = content;
        this.userAvatar = userAvatar;
        this.merAvatar = merAvatar;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
