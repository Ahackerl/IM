package com.netty.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "friendRelation")
public class FriendRelation {//好友关系
    @Id
    private String userId = "";
    private String friendId = "";
    private String comments = "";//备注 因为有备注 所以 一个好友关系插入两条记录
    private long createTime;
    private long deleteTime;


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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    @Override
    public String toString() {
        return "FriendRelation{" +
                ", userId='" + userId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", comments='" + comments + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}
