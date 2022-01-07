package com.netty.service.impl;

import com.netty.bean.*;
import com.netty.dao.impl.ChatDao;
import com.netty.service.IChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ChatService implements IChatService {

    @Resource
    private ChatDao chatDao;

    @Override
    public String addChat(Chat chat) {
        if (chat == null) {
            return null;
        }
        return chatDao.addChat(chat);
    }

    @Override
    public List<Chat> getChatBySpeakers(String talker, String friend) {

        return chatDao.getChatBySpeakers(talker, friend);
    }

    @Override
    public List<Chat> getGroupChat() {
        return chatDao.getGroupChat();
    }
}
