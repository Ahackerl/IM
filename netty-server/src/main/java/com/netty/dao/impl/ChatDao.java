package com.netty.dao.impl;

import com.netty.bean.*;
import com.netty.dao.IChatDao;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ChatDao implements IChatDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public String addChat(Chat chat) {
        Chat save = mongoTemplate.save(chat);
        return save.getChatId();
    }

    @Override
    public List<Chat> getGroupChat() {
        Query query = new Query();
        query.addCriteria(Criteria.where("friendId").exists(false));
        return mongoTemplate.find(query,Chat.class);
    }

    @Override
    public List<Chat> getChatBySpeakers(String talkerId, String friendId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").in(talkerId,friendId).and("friendId").in(talkerId,friendId));
        return mongoTemplate.find(query,Chat.class);
    }

}
