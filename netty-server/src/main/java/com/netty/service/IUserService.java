package com.netty.service;

import com.netty.bean.FriendRelation;
import com.netty.bean.User;

import java.util.List;

public interface IUserService {
    int insertUser(User user);
    int insertFriendRelation(FriendRelation friendRelation);
    List<User> findFriends(String userId);

    User findUserByName(String name);

    long updateIPByUserId(String userId,String IP);

    long updateOnlineStatusByUserName(String userName, int online);

    long logoutIP(String name);

    User findUserByIP(String IP);

    List<User> getUserListByOnlineStatus(String status);

    User findUserByID(String userId);
}
