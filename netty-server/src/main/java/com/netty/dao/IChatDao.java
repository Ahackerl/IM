package com.netty.dao;

import com.netty.bean.*;

import java.util.List;

public interface IChatDao {
    /**
     * 新加聊天记录
     * @param chat 聊天记录实例
     * @return 新添聊天记录的 chatId
     */
    String addChat(Chat chat);

    /**
     * 获得群聊消息
     * @return groupChats
     */
    List<Chat> getGroupChat();

    /**
     * 获取私聊消息
     * @param talkerId 发言者的 Id
     * @param friendId 对方的   Id
     * @return 所有私聊消息
     */
    List<Chat> getChatBySpeakers(String talkerId,String friendId);

}
