package com.netty.bean;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Group {
    @Id
    private String groupId;
    private String groupName;
    private int count = 0;
    List<String> userIds = new ArrayList<>();

    public Group() {

        count = userIds.size();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", count=" + count +
                ", userIds=" + userIds +
                '}';
    }
}
