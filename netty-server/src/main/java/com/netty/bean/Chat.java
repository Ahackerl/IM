package com.netty.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "chat")
public class Chat {
    private String chatId;
    private String userId;
    private String friendId;
    private String nickName;//昵称
    private String content;//聊天内容
    private long createTime;//发送时间
    private long deleteTime;//删除时间
    private int type;//消息类型 {0：私聊文本，1：私聊文件，2：群聊文本，3：群聊文件}

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId='" + chatId + '\'' +
                ", userId='" + userId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", type=" + type +
                '}';
    }
}
