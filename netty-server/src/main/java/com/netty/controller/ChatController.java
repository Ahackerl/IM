package com.netty.controller;

import com.alibaba.fastjson.JSONObject;
import com.netty.bean.*;
import com.netty.service.impl.ChatService;
import com.netty.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@ResponseBody
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;

    @PostMapping("/getWhisperChatInfos")
    public R getWhisperChatInfos(@RequestParam(name = "talker") String talker
            , @RequestParam(name = "friend") String friend) {
        System.out.println("talker = " + talker + "  friend = " + friend);
        if (talker == null || friend == null) {
            return R.error("参数错误");
        }
        List<Chat> chatInfos = chatService.getChatBySpeakers(talker, friend);
        System.out.println(JSONObject.toJSONString(chatInfos));
        return R.data(chatInfos);
    }

    @GetMapping("/getGroupChatInfos")
    public R getGroupChatInfos() {
        List<Chat> groupChat = chatService.getGroupChat();

        return R.data(groupChat);
    }



}





