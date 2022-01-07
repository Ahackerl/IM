package com.netty.service;

import com.netty.bean.*;

import java.util.List;


public interface IChatService {

    String addChat(Chat chat);

    List<Chat> getChatBySpeakers(String talker, String friend);

    List<Chat> getGroupChat();
}
