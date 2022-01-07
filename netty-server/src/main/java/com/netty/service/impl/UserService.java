package com.netty.service.impl;

import com.netty.bean.FriendRelation;
import com.netty.bean.User;
import com.netty.dao.impl.UserDao;
import com.netty.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public int insertUser(User user) {
        if (user == null) {
            return 0;
        }
        return userDao.saveUser(user);
    }

    @Override
    public int insertFriendRelation(FriendRelation friendRelation) {
        if (friendRelation == null) {
            return 0;
        }
        return userDao.saveFriendRelation(friendRelation);
    }

    @Override
    public List<User> findFriends(String userId) {
        List<User> users = new ArrayList<>();
        List<FriendRelation> friendRelations = userDao.findFriendRelations(userId);
        if (friendRelations != null && friendRelations.size() > 0) {
            for (FriendRelation f :
                    friendRelations) {
                String id = f.getFriendId();
                User user = userDao.findUserByUserId(id);
                if (user != null) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public User findUserByName(String name) {
        if (name == null) {
            return null;
        }
        return userDao.findUserByName(name);
    }

    @Override
    public long updateIPByUserId(String userId, String IP) {
        if (userId == null) {
            return 0;
        }
        return userDao.updateIPByUserId(userId, IP);
    }

    @Override
    public long updateOnlineStatusByUserName(String userName, int online) {
        if (userName == null || "".equals(userName)) {
            return 0;
        }
        return userDao.updateOnlineStatusByUserName(userName, online);
    }

    @Override
    public long logoutIP(String name) {
        if (name == null) {
            return 0;
        }
        return userDao.logoutIP(name);
    }

    @Override
    public User findUserByIP(String IP) {
        if (IP == null || "".equals(IP)) {
            return null;
        }
        return userDao.findUserByIP(IP);
    }

    @Override
    public List<User> getUserListByOnlineStatus(String status) {
        if ("-1".equals(status)) {
            return null;
        }
        return userDao.getUserListByOnlineStatus(status);
    }

    @Override
    public User findUserByID(String userId) {
        return userDao.findUserById(userId);
    }
}
