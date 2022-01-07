package com.netty.dao.impl;

import com.netty.bean.FriendRelation;
import com.netty.bean.User;
import com.netty.dao.IUserDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class UserDao implements IUserDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public User findUserByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findUserByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public int saveUser(User user) {
        User save = mongoTemplate.save(user);
        if (save.getUserId() != null && !"".equals(save.getUserId())) {
            return 1;
        }
        return 0;
    }

    @Override
    public int saveFriendRelation(FriendRelation friendRelation) {
        FriendRelation save = mongoTemplate.save(friendRelation);
        if (save.getUserId() != null && !"".equals(save.getUserId())) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<FriendRelation> findFriendRelations(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, FriendRelation.class);
    }

    @Override
    public long updateIPByUserId(String userId, String IP) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("userId").is(userId));
        update.set("IP", IP);
        return mongoTemplate.updateFirst(query, update, User.class).getMatchedCount();
    }

    @Override
    public long updateOnlineStatusByUserName(String name, int online) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("isOnline", online);
        if (online == 0){
            update.set("IP","NAN");
        }
        return mongoTemplate.updateFirst(query, update, User.class).getMatchedCount();
    }

    @Override
    public long logoutIP(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("IP", "NAN");
        return mongoTemplate.updateFirst(query, update, User.class).getMatchedCount();
    }

    @Override
    public User findUserByIP(String IP) {
        Query query = new Query();
        query.addCriteria(Criteria.where("IP").is(IP));
        return mongoTemplate.findOne(query,User.class);
    }

    @Override
    public List<User> getUserListByOnlineStatus(String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("isOnline").is(Integer.valueOf(status)));
        return mongoTemplate.find(query,User.class);
    }

    @Override
    public User findUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        return mongoTemplate.findOne(query,User.class);
    }
}
