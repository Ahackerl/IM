package com.netty.dao;

import com.netty.bean.FriendRelation;
import com.netty.bean.User;

import java.util.List;

public interface IUserDao {
    /**
     * 跟据 name 查找 user
     * @param name 姓名
     * @return user
     */
    User findUserByName(String name);

    /**
     * 跟据 userId 查找 user
     * @param userId
     * @return
     */
     User findUserByUserId(String userId);

    /**
     * 插入 user
     * @param user 用户
     * @return 0：失败 1：成功
     */
    int saveUser(User user);

    /**
     * 添加好友关系
     * @param friendRelation
     * @return
     */
    int saveFriendRelation(FriendRelation friendRelation);

    /**
     * 查找好友关系
     * @param userId
     * @return
     */
     List<FriendRelation> findFriendRelations(String userId);


    /**
     * 更新IP
     * @param userId 用户ID
     * @param IP IP
     * @return 是否更改 0否 1是
     */
    long updateIPByUserId(String userId,String IP);

    long updateOnlineStatusByUserName(String name,int online);

    long logoutIP(String name);

    /**
     * 通过 IP 找 User
     * @param IP 客户端的IP
     * @return user
     */
    User findUserByIP(String IP);

    /**
     * 获取好友列表
     * @param status 在线状态 1：在线  ， 0：不在线
     * @return 好友列表
     */
    List<User> getUserListByOnlineStatus(String status);

    User findUserById(String userId);
}
